import java.util.Date

enum Color:
  case Red
  case Green
  case Blue

enum ColorShort:
  case Red, Green, Blue

enum DayOfWeek:
  case Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday

def isWeekend(day: DayOfWeek) = day match
  case DayOfWeek.Saturday | DayOfWeek.Sunday => true
  case _                                     => false

isWeekend(DayOfWeek.Sunday)
isWeekend(DayOfWeek.Monday)

// Apparently we can use enums almost as a class constructor. The compiler
// expands the enum definition into an abstract class with a companion
// object.
enum Direction(val dx: Int, val dy: Int):
  // Note that we need to use extends for enum cases that pass parameters.
  case Right extends Direction(1, 0)
  case Up extends Direction(0, 1)
  case Left extends Direction(-1, 0)
  case Down extends Direction(0, -1)

  // ordinal is the ordinal rank of the a particular enum e in the Enum
  // definition.
  // Also .values is an immutable array in the companion object
  // that contains all enum values. This is why we index into it.
  def leftTurn = Direction.values((ordinal + 1) % 4)
end Direction

// The compiler expands this to roughly
// abstract class Direction2(val dx: Int, val dy: Int):
//   def leftTurn = Direction2.values((ordinal + 1) % 4)
// object Direction2:
//   val Right = new Direction2(1, 0) {}
//   val Up = new Direction2(0, 1) {}
//   val Left = new Direction2(-1, 0) {}
//   val Down = new Direction2(0, -1) {}
// end Direction2

val r = Direction.Right
val u = r.leftTurn
val v = (u.dx, u.dy)

// Pretty handy ways to define ADTs in domain modeling!
enum PaymentMethod:
  case CreditCard(kind: Card, holder: String, number: Long, expires: Date)
  case PayPal(email: String)
  case Cash

enum Card:
  case Visa, Mastercard, Amex

val myPay = PaymentMethod.PayPal("hello@world.com")
val myCash = PaymentMethod.Cash
