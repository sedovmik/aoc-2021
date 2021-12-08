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

        val signals = left
            .split(' ')

        val ds = hashMapOf<Int, Set<Char>>()

        signals.forEach { signal ->
            val set = signal.toSet()
            when (signal.length) {
                2 -> ds[1] = set
                4 -> ds[4] = set
                3 -> ds[7] = set
                7 -> ds[8] = set
            }
        }

        signals.forEach { signal ->
            if (signal.length == 6) {
                // 0, 6, 9
                val set = signal.toSet()
                if (set.containsAll(ds.getValue(4))) {
                    ds[9] = set
                } else if (set.containsAll(ds.getValue(7))) {
                    ds[0] = set
                } else {
                    ds[6] = set
                }
            }
        }

        signals.forEach { signal ->
            if (signal.length == 5) {
                // 3, 5, 2
                val set = signal.toSet()
                if (set.containsAll(ds.getValue(1))) {
                    ds[3] = set
                } else if (ds.getValue(9).containsAll(set)) {
                    ds[5] = set
                } else {
                    ds[2] = set
                }
            }
        }

        return right.split(' ')
            .map {
                val cs = it.toSet()
                ds.entries.find { e ->
                    e.value == cs
                }!!.key
            }.fold(0) { acc, d -> acc * 10 + d }
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

