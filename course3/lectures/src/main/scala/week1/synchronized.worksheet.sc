class Account(private var amount: Int = 0):
  val uid = Account.getUniqueId()

  def lockAndTransfer(target: Account, n: Int) =
    this.synchronized {
      target.synchronized {
        this.amount -= n
        target.amount += n
      }
    }

  def transfer(target: Account, n: Int) =
    if this.uid < target.uid then this.lockAndTransfer(target, n)
    else target.lockAndTransfer(this, -n)

  def getAmount(): Int = amount

def startThread(a: Account, b: Account, n: Int) =
  val t = new Thread {
    override def run() =
      for i <- 0 until n do a.transfer(b, 1)
  }
  t.start()
  t

object Account:
  private var uid = 0L
  def getUniqueId(): Long =
    uid += 1
    uid

val a1 = Account(500000)
val a2 = Account(700000)

val t = startThread(a1, a2, 150001)
val s = startThread(a2, a1, 150000)
t.join()
s.join()

a1.getAmount()
a2.getAmount()
