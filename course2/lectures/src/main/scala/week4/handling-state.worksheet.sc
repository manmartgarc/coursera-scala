var x: String = "abc"
var count = 111

x
count

x = "hi"
count += 1
x
count

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

val account = BankAccount()
account.deposit(100)
account.withdraw(80)
account.withdraw(20)
account
