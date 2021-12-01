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

    readInput("Day01_test") {
        check(part1(it) == 7)
    }

    readInput("Day01") {
        println(part1(it))
    }

    readInput("Day01") {
        println(part2(it))
    }
}
