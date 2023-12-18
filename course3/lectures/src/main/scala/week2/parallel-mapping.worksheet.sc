List(1, 3, 8).map(x => x * x)
List(1, 3, 8).foldLeft(1)((s, x) => s * x)
List(1, 3, 9).scan(0)((s, x) => s + x)