fun main() {

    class Accumulator(size: Int) {
        var total = 0
        val counters = IntArray(size)

        fun append(next: String) {
            val array = next.toCharArray()
            var idx = 0
            for (ch in array) {
                if (ch == '1') {
                    counters[idx]++
                }
                idx += 1
            }

            total += 1
        }

        fun prod(): Int {
            var gamma = 0
            var epsilon = 0
            for (idx in counters) {
                if (2 * idx >= total) {
                    gamma = 1 + (gamma shl 1)
                    epsilon = epsilon shl 1
                } else {
                    gamma = gamma shl 1
                    epsilon = 1 + (epsilon shl 1)
                }
            }

            return gamma * epsilon
        }
    }

    fun part1(input: Sequence<String>, size: Int): Int {
        val acc = Accumulator(size)
        for (s in input) {
            acc.append(s)
        }
        return acc.prod()
    }

    fun part2(input: Sequence<String>, size: Int): Int {
        fun walk(sorted: List<String>, size: Int, compare: (Int, Int) -> Boolean): Int {
            var left = 0;
            var right = sorted.size

            for (i in 0 until size) {
                val result = -1 - sorted.binarySearch(left, right)
                    { str -> if (str[i] == '1') 1 else -1 }
                if (compare(result - left, right - result)) {
                    right = result
                } else {
                    left = result
                }

                if (left == right - 1) {
                    break
                }
            }

            return sorted[left].toInt(2)
        }

        val sorted = input.toList().sorted()

        val oxygen = walk(sorted, size) { a, b -> a > b }
        val co2 = walk(sorted, size) { a, b -> a <= b }

        return oxygen * co2
    }

    test("Day03_test", 198) {
        part1(it, 5)
    }

    solve("Day03") {
        part1(it, 12)
    }

    test("Day03_test", 230) {
        part2(it, 5)
    }

    solve("Day03") {
        part2(it, 12)
    }
}
