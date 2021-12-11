import java.util.*

fun main() {

    class Grid(val arr: MutableList<MutableList<Int>>) {

        val offsets = listOf(
            1 to 0,
            1 to 1,
            0 to 1,
            -1 to 1,
            -1 to 0,
            -1 to -1,
            0 to -1,
            1 to -1,
        )

        fun step(): Int {
            val queue = LinkedList<Pair<Int, Int>>()
            for ((i, row) in arr.withIndex()) {
                for (j in row.indices) {
                    inc(i, j, queue)
                }
            }

            while (queue.isNotEmpty()) {
                val pair = queue.poll()
                offsets.map { it.first + pair.first to it.second + pair.second }
                    .filter { it.first in arr.indices && it.second in arr[it.first].indices }
                    .forEach {
                        inc(it.first, it.second, queue)
                    }
            }

            var flashes = 0
            for ((i, row) in arr.withIndex()) {
                for ((j, value) in row.withIndex()) {
                    if (value == 10) {
                        arr[i][j] = 0
                        flashes++
                    }
                }
            }

            return flashes
        }

        private fun inc(i: Int, j: Int, queue: LinkedList<Pair<Int, Int>>) {
            if (arr[i][j] < 10) {
                arr[i][j]++
                if (arr[i][j] == 10) {
                    queue.add(i to j)
                }
            }
        }

        override fun toString(): String {
            return arr.joinToString("\n") { it.toString() }
        }
    }

    fun part1(input: Sequence<String>): Int {
        val arr = input.map { it.map(Char::digitToInt).toMutableList() }
            .toMutableList()

        val grid = Grid(arr)

        var flashes = 0
        repeat(100) {
            flashes += grid.step()
        }

        return flashes
    }

    fun part2(input: Sequence<String>): Int {
        val arr = input.map { it.map(Char::digitToInt).toMutableList() }
            .toMutableList()

        val grid = Grid(arr)

        var step = 1
        while (grid.step() != 100) {
            step++
        }

        return step
    }

    test("Day11_test", 1656) {
        part1(it)
    }

    solve("Day11") {
        part1(it)
    }

    test("Day11_test", 195) {
        part2(it)
    }

    solve("Day11") {
        part2(it)
    }
}
