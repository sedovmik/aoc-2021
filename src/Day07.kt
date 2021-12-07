import kotlin.math.abs

fun main() {

    fun part1(input: Sequence<String>): Int {
        val ns = input.first()
            .split(',')
            .map(String::toInt)

        return ns.minOf { n -> ns.sumOf { abs(it - n) } }
    }

    fun part2(input: Sequence<String>): Int {
        val ns = input.first()
            .split(',')
            .map(String::toInt)

        val fuel = { d: Int -> d * (d + 1) / 2 }
        return (0..ns.maxOf { it })
            .minOf { n -> ns.sumOf { fuel(abs(it - n)) } }
    }

    test("Day07_test", 37) {
        part1(it)
    }

    solve("Day07") {
        part1(it)
    }

    test("Day07_test", 168) {
        part2(it)
    }

    solve("Day07") {
        part2(it)
    }
}

