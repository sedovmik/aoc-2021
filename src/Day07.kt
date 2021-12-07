import org.apache.commons.math3.stat.descriptive.moment.Mean
import org.apache.commons.math3.stat.descriptive.rank.Median
import kotlin.math.abs
import kotlin.math.roundToInt

fun main() {

    fun part1(input: Sequence<String>): Int {
        val ns = input.first()
            .split(',')
            .map(String::toDouble)
            .toDoubleArray()

        val median = Median().evaluate(ns)
        return ns.sumOf { abs(it - median) }
            .roundToInt()
    }

    fun part2(input: Sequence<String>): Int {
        val ns = input.first()
            .split(',')
            .map(String::toDouble)
            .toDoubleArray()

        val mean = Mean().evaluate(ns)
        val fuel = { d: Double -> d * (d + 1) / 2}
        return ns.sumOf { fuel(abs(it - mean)) }
            .roundToInt()
    }

    test("Day07_test", 37) {
        part1(it)
    }

    solve("Day07") {
        part1(it)
    }

    test("Day07_test", 168) {
        part2(it)
    }

    solve("Day07") {
        part2(it)
    }
}

