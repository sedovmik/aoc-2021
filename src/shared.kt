import java.io.File
import java.lang.Integer.min
import java.util.*
import kotlin.math.abs

fun <K> MutableMap<K, Long>.inc(key: K, delta: Long) =
    merge(key, delta, Long::plus)

fun <K> Iterable<K>.freq(): Map<K, Long> = fold(hashMapOf()) { acc, v ->
    acc.also { acc.inc(v, 1) }
}

fun <T : Any> test(name: String, expected: T, solver: (Sequence<String>) -> T) =
    File("input", "$name.txt").useLines {
        val startTime = System.currentTimeMillis()
        val result = solver(it)
        if (expected != result) {
            error(
                "$name Failed: $result != $expected " +
                        "[${System.currentTimeMillis() - startTime}ms]"
            )
        } else {
            println(
                "$name Passed ($expected) " +
                        "[${System.currentTimeMillis() - startTime}ms]"
            )
        }
    }

fun <T : Any> solve(name: String, solver: (Sequence<String>) -> T) =
    File("input", "$name.txt").useLines {
        val startTime = System.currentTimeMillis()
        val result = solver(it)
        println("$result [${System.currentTimeMillis() - startTime}ms]")
    }


data class Vec2(val x: Int, val y: Int) {
    operator fun plus(p: Vec2) = Vec2(p.x + x, p.y + y)
}

fun <T> List<List<T>>.contains(point: Vec2) = point.x in this.indices && point.y in this[point.x].indices

data class Line(val a: Vec2, val b: Vec2) {

    val isDiagonal = a.x != b.x && a.y != b.y

    // bresenham
    fun plot(): Sequence<Vec2> = sequence {
        val dx = abs(b.x - a.x)
        val dy = abs(b.y - a.y)

        val sx = if (a.x < b.x) 1 else -1
        val sy = if (a.y < b.y) 1 else -1

        var err = dx - dy

        var cx = a.x
        var cy = a.y

        while (true) {
            yield(Vec2(cx, cy))
            if (cx == b.x && cy == b.y) break
            val e2 = 2 * err
            if (e2 >= -dy) {
                err -= dy
                cx += sx
            }
            if (e2 <= dx) {
                err += dx
                cy += sy
            }
        }
    }
}

class Grid<T>(val matrix: List<List<T>>) {

    val h = matrix.size
    val w = matrix[0].size

    private val offsets = listOf(
        Vec2(1, 0), Vec2(-1, 0),
        Vec2(0, 1), Vec2(0, -1)
    )

    val Vec2.d: Int
        get() = this.y * w + this.x

    fun neighbours(p: Vec2) =
        offsets.map { it + p }.filter(matrix::contains)

    operator fun List<List<T>>.get(p: Vec2) = this[p.y][p.x]

    companion object {
        fun intGridFromDigits(lines: List<String>): Grid<Int> {
            return Grid(lines.map { line ->
                line.map { it.digitToInt() }
            })
        }
    }
}

fun Grid<Int>.dijkstra(src: Vec2, dst: Vec2): Int {
    val count = w * h
    val visited = BooleanArray(count)
    val distance = IntArray(count) { Int.MAX_VALUE }
    distance[src.d] = 0

    val pq = PriorityQueue<Vec2>(count) { a, b ->
        distance[a.d].compareTo(distance[b.d])
    }

    pq.add(src)

    while (pq.isNotEmpty()) {
        val p = pq.remove()
        if (!visited[p.d]) {
            visited[p.d] = true
            neighbours(p).forEach {
                distance[it.d] = min(distance[it.d], distance[p.d] + matrix[it])
                if (!visited[it.d]) {
                    pq.add(it)
                }
            }
        }
    }

    return distance[dst.d]
}
