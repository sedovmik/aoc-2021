fun main() {

    fun solve(input: Sequence<String>, n: Int): Long {
        val list = input.toList()
        val template = list.first()
        val rules = list
            .drop(2)
            .fold(hashMapOf<String, Pair<String, String>>()) { acc, rule ->
                val (from, res) = rule.split(" -> ")
                acc.also {
                    it[from] = (from[0] + res) to (res + from[1])
                }
            }

        var current = template.windowed(2).freq()

        repeat(n) {
            val step = hashMapOf<String, Long>()
            for (e in current) {
                val next = rules[e.key]!!
                step.merge(next.first, e.value, Long::plus)
                step.merge(next.second, e.value, Long::plus)
            }
            current = step
        }

        val freq = hashMapOf(template.last() to 1L)
        for (e in current) {
            freq.merge(e.key[0], e.value, Long::plus)
        }

        return freq.maxOf { it.value } - freq.minOf { it.value }
    }

    fun part1(input: Sequence<String>) = solve(input, 10)

    fun part2(input: Sequence<String>) = solve(input, 40)

    test("Day14_test", 1588) {
        part1(it)
    }

    solve("Day14") {
        part1(it)
    }

    test("Day14_test", 2188189693529) {
        part2(it)
    }

    solve("Day14") {
        part2(it)
    }
}
