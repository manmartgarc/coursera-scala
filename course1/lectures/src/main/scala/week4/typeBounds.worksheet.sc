import week3.class_hierarchies.IntSet

// <: IntSet is an upper bound of the type parameter S.
// S <: T means: S is a subtype of T
// S >: T means: S is a supertype of T or T is a subtype of S.
// assertAllPos takes all empty sets to empty sets and all non-empty sets to
// non-empty sets.
def assertAllPos[S <: IntSet](r: S): S = ???
