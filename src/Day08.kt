fun main() {

    fun countAnchorDigits(input: String): Int {
        val (_, right) = input.split(" | ")
        val anchorLens = setOf(2, 3, 4, 7)
        return right
            .split(' ')
            .count { anchorLens.contains(it.length) }
    }

    fun part1(input: Sequence<String>): Int {
        return input.map(::countAnchorDigits)
            .sum()
    }

    fun decodeLine(input: String): Int {

        val (left, right) = input.split(" | ")

        val freq = left
            .fold(hashMapOf<Char, Int>()) { acc, ch ->
                acc.merge(ch, 1, Int::plus)
                acc
            }

        val remap = { str: String ->
            val ns = str.map { ch -> freq.getValue(ch) }
                .sorted()

            when (ns) {
                listOf(4, 6, 7, 8, 8, 9) -> 0
                listOf(8, 9) -> 1
                listOf(4, 7, 7, 8, 8) -> 2
                listOf(7, 7, 8, 8, 9) -> 3
                listOf(6, 7, 8, 9) -> 4
                listOf(6, 7, 7, 8, 9) -> 5
                listOf(4, 6, 7, 7, 8, 9) -> 6
                listOf(8, 8, 9) -> 7
                listOf(4, 6, 7, 7, 8, 8, 9) -> 8
                listOf(6, 7, 7, 8, 8, 9) -> 9
                else -> error(str)
            }
        }

        return right.split(' ')
            .map(remap)
            .fold(0) { acc, n -> acc * 10 + n }
    }

    fun part2(input: Sequence<String>): Int {
        return input.map(::decodeLine)
            .sum()
    }

    test("Day08_test", 26) {
        part1(it)
    }

    solve("Day08") {
        part1(it)
    }

    test("Day08_test", 61229) {
        part2(it)
    }

    solve("Day08") {
        part2(it)
    }
}

