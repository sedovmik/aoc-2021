fun main() {

    fun parse(input: String): Line {
        val (start, end) = input.split(" -> ")
        val (ax, ay) = start.split(",").map(String::toInt)
        val (bx, by) = end.split(",").map(String::toInt)
        return Line(Vec2(ax, ay), Vec2(bx, by))
    }

    fun countPoint(lines: Sequence<Line>, includeDiagonal: Boolean): Int {
        return lines.filter { !it.isDiagonal || includeDiagonal }
            .flatMap { it.plot() }
            .fold(hashMapOf<Vec2, Int>()) { acc, point ->
                acc.merge(point, 1, Int::plus)
                acc
            }
            .values
            .count { it >= 2 }
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

