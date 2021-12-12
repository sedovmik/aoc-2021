fun main() {

    fun part1(input: Sequence<String>): Int {
        val adj = input.map {
            val (a, b) = it.split('-')
            a to b
        }.fold(hashMapOf<String, List<String>>()) { acc, pair ->
            acc.merge(pair.first, listOf(pair.second), List<String>::plus)
            acc.merge(pair.second, listOf(pair.first), List<String>::plus)
            acc
        }

        val visited = mutableSetOf("start")

        fun count(
            current: Int,
            from: String
        ): Int {
            if (from == "end") {
                return current + 1
            }

            var result = current
            for (v in adj.getOrDefault(from, listOf())) {
                if (v.lowercase() != v || !visited.contains(v)) {
                    visited.add(v)
                    result = count(result, v)
                    visited.remove(v)
                }
            }

            return result
        }

        return count(0, "start")
    }


    fun part2(input: Sequence<String>): Int {
        val adj = input.map {
            val (a, b) = it.split('-')
            a to b
        }.fold(hashMapOf<String, List<String>>()) { acc, pair ->
            acc.merge(pair.first, listOf(pair.second), List<String>::plus)
            acc.merge(pair.second, listOf(pair.first), List<String>::plus)
            acc
        }

        val path = mutableListOf("start")

        fun count(
            current: Int,
            from: String,
        ): Int {
            if (from == "end") {
                return current + 1
            }

            var result = current
            for (v in adj.getOrDefault(from, listOf())) {
                val allowed = if (v.lowercase() == v) {
                    val visitedTwice = path.groupBy { it }
                        .any { it.key.lowercase() == it.key && it.value.size > 1 }
                    if (visitedTwice) !path.contains(v) else true
                } else {
                    true
                }

                if (v != "start" && allowed) {
                    path.add(v)
                    result = count(result, v)
                    path.removeLast()
                }
            }

            return result
        }

        return count(0, "start")
    }

    test("Day12_test_a", 10) {
        part1(it)
    }

    test("Day12_test_b", 19) {
        part1(it)
    }

    solve("Day12") {
        part1(it)
    }

    test("Day12_test_a", 36) {
        part2(it)
    }

    test("Day12_test_b", 103) {
        part2(it)
    }

    test("Day12_test_c", 3509) {
        part2(it)
    }

    solve("Day12") {
        part2(it)
    }
}
