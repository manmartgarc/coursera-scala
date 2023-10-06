// Say C[T] is a parametrized type. A, B are such that A <: B
// C[A] <: C[B] -> covariant
// C[A] >: C[B] -> contravariant
// neither -> nonvariant
// we can annotate this with
// class C[+A] -> covariant
// class C[-A] -> contravariant
// class C[A] -> nonvariant

trait Fruit
class Apple extends Fruit
class Orange extends Fruit

type FtoO = Fruit => Orange
type AtoF = Apple => Fruit

// This means that FtoO <: AtoF
// AtoF <: FtoO is not true because I cannot do everything I can do with
// FtoO with AtoF, i.e. Liskov substitution principle.