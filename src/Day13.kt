fun main() {

    data class Point(val x: Int, val y: Int)
    data class Fold(val d: String, val v: Int) {
        fun flip(p: Point): Point {
            return when (d) {
                "x" -> if (p.x <= v) p else Point(p.x - 2 * (p.x - v), p.y)
                "y" -> if (p.y <= v) p else Point(p.x, p.y - 2 * (p.y - v))
                else -> error(d)
            }
        }
    }

    fun parse(input: Sequence<String>): Pair<List<Point>, List<Fold>> {

        val lines = input.toList()

        val dots = lines.takeWhile { it.isNotBlank() }
            .map {
                val (x, y) = it.split(',')
                Point(x.toInt(), y.toInt())
            }

        val folds = lines.filter { it.startsWith("fold along ") }
            .map {
                val (d, v) = it.removePrefix("fold along ").split('=')
                Fold(d, v.toInt())
            }

        return dots to folds
    }

    fun draw(dots: List<Point>) {
        val width = dots.maxOf { it.x }
        val height = dots.maxOf { it.y }

        for (y in 0..height) {
            for (x in 0..width) {
                if (dots.any { it.x == x && it.y == y }) print('#') else print('.')
            }
            println()
        }
    }

    fun part1(input: Sequence<String>): Int {
        val (dots, folds) = parse(input)
        val fold = folds.first()
        return dots.map(fold::flip).distinct().count()
    }

    fun part2(input: Sequence<String>) {
        val (dots, folds) = parse(input)
        draw(folds.fold(dots) { acc, fold ->
            acc.map(fold::flip).distinct()
        })
    }

    test("Day13_test", 17) {
        part1(it)
    }

    solve("Day13") {
        part1(it)
    }

    solve("Day13") {
        part2(it)
    }
}
