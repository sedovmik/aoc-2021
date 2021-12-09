fun main() {

    fun part1(input: Sequence<String>): Int {
        val heightmap = input.map {
            it.toCharArray()
                .map(Char::digitToInt)
        }.toList()

        return heightmap.mapIndexed { i, list ->
            list.mapIndexed { j, value ->
                val isLowest = listOf(i - 1 to j, i + 1 to j, i to j - 1, i to j + 1)
                    .filter { (x, y) -> x in heightmap.indices && y in list.indices }
                    .all { heightmap[it.first][it.second] > value }
                if (isLowest) value + 1 else 0
            }
        }.flatten().sum()
    }

    fun part2(input: Sequence<String>): Int {
        val heightmap = input.map {
            it.toCharArray()
                .map(Char::digitToInt)
        }.toList()

        val visited = Array(heightmap.size) { BooleanArray(heightmap.first().size) }

        fun dfs(i: Int, j: Int): Int {
            if (i in heightmap.indices && j in heightmap[i].indices &&
                heightmap[i][j] < 9 && !visited[i][j]
            ) {
                visited[i][j] = true
                return 1 + dfs(i, j + 1) + dfs(i + 1, j) + dfs(i, j - 1) + dfs(i - 1, j)
            }

            return 0
        }

        return heightmap.mapIndexed { i, list ->
            list.mapIndexed { j, value ->
                val isLowest = listOf(i - 1 to j, i + 1 to j, i to j - 1, i to j + 1)
                    .filter { (x, y) -> x in heightmap.indices && y in list.indices }
                    .all { heightmap[it.first][it.second] > value }
                if (isLowest) dfs(i, j) else 0
            }
        }.flatten()
            .sortedDescending()
            .take(3)
            .reduce(Int::times)
    }

    test("Day09_test", 15) {
        part1(it)
    }

    solve("Day09") {
        part1(it)
    }

    test("Day09_test", 1134) {
        part2(it)
    }

    solve("Day09") {
        part2(it)
    }
}

