import java.io.File
import kotlin.math.abs

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
            println("$name Passed ($expected) " +
                    "[${System.currentTimeMillis() - startTime}ms]")
        }
    }

fun <T : Any> solve(name: String, solver: (Sequence<String>) -> T) =
    File("input", "$name.txt").useLines {
        val startTime = System.currentTimeMillis()
        val result = solver(it)
        println("$result [${System.currentTimeMillis() - startTime}ms]")
    }


data class Point(val x: Int, val y: Int)

data class Line(val a: Point, val b: Point) {

    val isDiagonal = a.x != b.x && a.y != b.y

    // bresenham
    fun plot(): Sequence<Point> = sequence {
        val dx = abs(b.x - a.x)
        val dy = abs(b.y - a.y)

        val sx = if (a.x < b.x) 1 else -1
        val sy = if (a.y < b.y) 1 else -1

        var err = dx - dy

        var cx = a.x
        var cy = a.y

        while (true) {
            yield(Point(cx, cy))
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