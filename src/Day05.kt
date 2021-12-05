
fun main() {

    fun parse(input: String): Line {
        val (start, end) = input.split(" -> ")
        val (ax, ay) = start.split(",").map(String::toInt)
        val (bx, by) = end.split(",").map(String::toInt)
        return Line(Point(ax, ay), Point(bx, by))
    }

    fun countPoint(lines: Sequence<Line>, includeDiagonal: Boolean): Int {
        val idx = hashMapOf<Point, Int>()
        lines.forEach { line ->
            if (!line.isDiagonal || includeDiagonal) {
                for (point in line.plot()) {
                    idx.merge(point, 1, Int::plus)
                }
            }
        }

        return idx.filterValues { it >= 2 }.count()
    }

    fun part1(input: Sequence<String>): Int {
        return countPoint(input.map(::parse), includeDiagonal = false)
    }

    fun part2(input: Sequence<String>): Int {
        return countPoint(input.map(::parse), includeDiagonal = true)
    }

    test("Day05_test", 5) {
        part1(it)
    }

    solve("Day05") {
        part1(it)
    }

    test("Day05_test", 12) {
        part2(it)
    }

    solve("Day05") {
        part2(it)
    }
}

