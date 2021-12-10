
import java.util.*

sealed interface Result {
    fun cost(): Long
}

class SyntaxError(private val char: Char) : Result {
    override fun cost() =
        when (char) {
            ')' -> 3L
            ']' -> 57L
            '}' -> 1197L
            '>' -> 25137L
            else -> error(char)
        }
}

class Valid(private val incomplete: List<Char>) : Result {

    private fun charConst(ch: Char) = when (ch) {
        ')' -> 1
        ']' -> 2
        '}' -> 3
        '>' -> 4
        else -> error(ch)
    }

    override fun cost(): Long {
        return incomplete.fold(0L) { acc, c ->
            acc * 5 + charConst(c)
        }
    }
}

fun main() {

    val brackets = mapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}',
        '<' to '>',
    )

    fun validate(line: String): Result {
        val stack = Stack<Char>()
        for (ch in line) {
            if (ch in brackets.keys) {
                stack.push(brackets.getValue(ch))
            } else {
                if (stack.isEmpty() || stack.peek() != ch) {
                    return SyntaxError(ch)
                } else {
                    stack.pop()
                }
            }
        }

        return Valid(stack.reversed())
    }

    fun part1(input: Sequence<String>): Long {
        return input.map(::validate)
            .filterIsInstance<SyntaxError>()
            .sumOf { it.cost() }
    }

    fun part2(input: Sequence<String>): Long {
        val sorted = input.map(::validate)
            .filterIsInstance<Valid>()
            .map { it.cost() }
            .sorted()
            .toList()

        return sorted[sorted.size / 2]
    }

    test("Day10_test", 26397) {
        part1(it)
    }

    solve("Day10") {
        part1(it)
    }

    test("Day10_test", 288957) {
        part2(it)
    }

    solve("Day10") {
        part2(it)
    }
}
