val E = 10

val x = E; val y = E
x == y

val a = E; val b = a
a == b

// This is called "referential transparency" (RT) because the expression
// "a == b" can be replaced by its value (true) without changing the
// meaning of the program. Not okay with stateful objects.

class BankAccount:
  private var balance = 0

  def deposit(amount: Int): Unit =
    if amount > 0 then balance = balance + amount

  def withdraw(amount: Int): Int =
    if 0 < amount && amount <= balance then
      balance = balance - amount
      balance
    else throw new Error("insufficient funds")

  override def toString(): String = s"Balance: $$$balance"

val accountA = BankAccount()
val accountB = BankAccount()
accountA == accountB