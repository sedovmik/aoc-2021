fun main() {

    class Board(nums: List<String>) {
        val xs = Array<MutableSet<Int>>(5) { mutableSetOf() }
        val ys = Array<MutableSet<Int>>(5) { mutableSetOf() }

        init {
            for ((no, line) in nums.withIndex()) {
                val ints = line.trim().split("\\s+".toRegex()).map(String::toInt)
                for ((i, num) in ints.withIndex()) {
                    xs[no].add(num)
                    ys[i].add(num)
                }
            }
        }

        fun draw(number: Int): Int {
            var bingo = false
            for (x in xs) {
                if (x.remove(number)) {
                    bingo = x.isEmpty() or bingo
                }
            }

            for (y in ys) {
                if (y.remove(number)) {
                    bingo = y.isEmpty() or bingo
                }
            }

            return if (bingo) {
                number * xs.sumOf(Set<Int>::sum)
            } else {
                0
            }
        }
    }

    fun read(input: Sequence<String>): Pair<List<Int>, MutableList<Board>> {
        val it = input.iterator()

        val boards = mutableListOf<Board>()

        val numbers = it.next().split(',').map(String::toInt)
        while (it.hasNext()) {
            check(it.next() == "")
            boards.add(Board(listOf(
                it.next(),
                it.next(),
                it.next(),
                it.next(),
                it.next(),
            )))
        }

        return numbers to boards
    }

    fun part1(input: Sequence<String>): Int {
        val (numbers, boards) = read(input)

        for (number in numbers) {
            for (board in boards) {
                val ret = board.draw(number)
                if (ret > 0) {
                    return ret
                }
            }
        }

        return 0
    }

    fun part2(input: Sequence<String>): Int {
        val (numbers, boards) = read(input)
        var cur = boards

        for (number in numbers) {
            val it = boards.iterator()
            while (it.hasNext()) {
                val ret = it.next().draw(number)
                if (ret > 0) {
                    it.remove()
                    if (boards.isEmpty()) {
                        return ret
                    }
                }
            }
        }

        return 0
    }

    readInput("Day04_test") {
        check(part1(it) == 4512)
    }

    readInput("Day04") {
        println(part1(it))
    }

    readInput("Day04_test") {
        check(part2(it) == 1924)
    }

    readInput("Day04") {
        println(part2(it))
    }
}
