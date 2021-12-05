fun main() {
    fun part1(input: Sequence<String>): Int {
        return input
            .map(String::toInt)
            .windowed(2) { w -> w[0] < w[1]}
            .count { it }
    }

    fun part2(input: Sequence<String>): Int {
        return input
            .map(String::toInt)
            .windowed(4) { w -> w[0] < w[3]}
            .count { it }
    }

    test("Day01_test", 7) {
        part1(it)
    }

    solve("Day01") {
        part1(it)
    }

    solve("Day01") {
        part2(it)
    }
}
