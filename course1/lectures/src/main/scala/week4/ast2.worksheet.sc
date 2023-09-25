// trait Expr

// We can use a companion object for the trait, so as not to pollute
// the global names space. We can still use `import Expr.*` to import all the
// case classes.
// These are called algebraic data types or ADTs.
// object Expr:
//   case class Var(s: String) extends Expr
//   case class Number(n: Int) extends Expr
//   case class Sum(e1: Expr, e2: Expr) extends Expr
//   case class Prod(e1: Expr, e2: Expr) extends Expr

// We can also use Enums in scala to define ADTs
enum Expr:
  case Var(s: String) extends Expr
  case Number(n: Int) extends Expr
  case Sum(e1: Expr, e2: Expr) extends Expr
  case Prod(e1: Expr, e2: Expr) extends Expr

def show(e: Expr): String = e match
  case Expr.Var(x)     => x
  case Expr.Number(n)  => n.toString
  case Expr.Sum(a, b)  => s"${show(a)} + ${show(b)}"
  case Expr.Prod(a, b) => s"${showP(a)} * ${showP(b)}"

def showP(e: Expr): String = e match
  case e: Expr.Sum => s"(${show(e)})"
  case _           => show(e)

def eval(e: Expr): Int = e match
  case Expr.Number(n)   => n
  case Expr.Sum(e1, e2) => eval(e1) + eval(e2)

val expr = Expr.Sum(Expr.Number(1), Expr.Number(1))
eval(expr)
show(expr)
val expr1 = Expr.Prod(expr, Expr.Var("x"))
show(expr1)
