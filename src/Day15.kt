fun main() {

    fun Grid<Int>.extend(n: Int): Grid<Int> {
        return Grid(List(h * n) { y ->
            MutableList(w * n) { x ->
                val manh = y / h + x / w
                (matrix[y % h][x % w] + manh - 1) % 9 + 1
            }
        })
    }

    fun part1(input: Sequence<String>): Int {
        val grid = Grid.intGridFromDigits(input.toList())
        return grid.dijkstra(Vec2(0, 0), Vec2(grid.w - 1, grid.h - 1))
    }

    fun part2(input: Sequence<String>): Int {
        val grid = Grid.intGridFromDigits(input.toList()).extend(5)
        return grid.dijkstra(Vec2(0, 0), Vec2(grid.w - 1, grid.h - 1))
    }

    test("Day15_test", 40) {
        part1(it)
    }

    solve("Day15") {
        part1(it)
    }

    test("Day15_test", 315) {
        part2(it)
    }

    solve("Day15") {
        part2(it)
    }
}


