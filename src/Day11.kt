import java.util.*

fun main() {

    fun Grid<Int>.step(): Int {

        val queue = LinkedList<Vec2>()
        val inc = { i: Int, j: Int ->
            if (matrix[i][j] < 10) {
                matrix[i][j]++
                if (matrix[i][j] == 10) {
                    queue.add(Vec2(i, j))
                }
            }
        }

        forEach(inc)

        while (queue.isNotEmpty()) {
            val v = queue.poll()
            neighbours8(v).forEach {
                inc(it.x, it.y)
            }
        }

        var flashes = 0
        forEach { i, j ->
            if (matrix[i][j] == 10) {
                matrix[i][j] = 0
                flashes++
            }
        }

        return flashes
    }

    fun part1(input: Sequence<String>): Int {
        val grid = Grid.intGridFromDigits(input.toList())

        var flashes = 0
        repeat(100) {
            flashes += grid.step()
        }

        return flashes
    }

    fun part2(input: Sequence<String>): Int {
        val grid = Grid.intGridFromDigits(input.toList())

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
