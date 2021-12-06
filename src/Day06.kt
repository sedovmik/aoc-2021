import java.util.*

fun main() {

    fun solve(list: MutableList<Long>, days: Int): Long {
        repeat(days) {
            Collections.rotate(list, -1)
            list[6] += list[8]
        }

        return list.sum()
    }

    fun readArray(input: Sequence<String>): MutableList<Long> {
        val line = input.first()
        val list = MutableList<Long>(9) { 0 }
        for (ch in line.toCharArray()) {
            if (ch in '0'..'8') {
                list[ch - '0']++
            }
        }
        return list
    }

    fun part1(input: Sequence<String>): Long {
        return solve(readArray(input), 80)
    }

    fun part2(input: Sequence<String>): Long {
        return solve(readArray(input), 256)
    }

    test("Day06_test", 5934) {
        part1(it)
    }

    solve("Day06") {
        part1(it)
    }

    test("Day06_test", 26984457539) {
        part2(it)
    }

    solve("Day06") {
        part2(it)
    }
}

