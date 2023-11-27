object frp:

  trait Signal[+T]:
    def apply(): Signal.Observed[T]

  object Signal:
    opaque type Observer = AbstractSignal[?]
    type Observed[T] = Observer ?=> T
    def caller(using o: Observer) = o

    abstract class AbstractSignal[+T] extends Signal[T]:
      private var currentValue: T = _
      private var observers: Set[Observer] = Set.empty

      protected def eval: Observed[T]

      protected def computeValue(): Unit =
        val newValue = eval(using this)
        val observeChange = observers.nonEmpty && newValue != currentValue
        currentValue = newValue
        if observeChange then
          val obs = observers
          observers = Set.empty
          obs.foreach(_.computeValue())

      def apply(): Observed[T] =
        observers += caller
        assert(!caller.observers.contains(this), "cyclic signal definition")
        currentValue
    end AbstractSignal

    def apply[T](expr: => Observed[T]): Signal[T] = new AbstractSignal[T]:
      val eval = expr
      computeValue()

    class Var[T](expr: => Observed[T]) extends AbstractSignal[T]:
      protected var eval = expr
      computeValue()

      def update(expr: => T): Unit =
        eval = expr
        computeValue()
    end Var

    given noObserver: Observer = new AbstractSignal[Nothing]:
      override def eval = ???
      override def computeValue() = ()
  end Signal
end frp

import frp._

class BankAccount:
  def balance: Signal[Int] = myBalance

  private val myBalance = Signal.Var[Int](0)

  def deposit(amount: Int): Unit =
    if amount > 0 then
      val b = myBalance()
      myBalance() = b + amount

  def withdraw(amount: Int): Unit =
    if 0 < amount && amount <= balance() then
      val b = myBalance()
      myBalance() = b - amount
    else throw new Error("insufficient funds")
end BankAccount

def consolidated(accts: List[BankAccount]): Signal[Int] =
  Signal(accts.map(_.balance()).sum)

val a = BankAccount()
val b = BankAccount()
val c = consolidated(List(a, b))
c()
a.deposit(10)
c()
b.deposit(20)
c()
a.withdraw(8)
c()
