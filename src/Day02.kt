fun main() {

    class Command(line: String) {
        val type: String
        val amount: Int

        init {
            val words = line.split(' ')
            type = words[0]
            amount = words[1].toInt()
        }
    }

    class Position {
        var pos = 0
        var depth = 0
        var aim = 0

        fun apply(command: Command): Position {
            when (command.type) {
                "forward" -> pos += command.amount
                "up" -> depth -= command.amount
                "down" -> depth += command.amount
            }
            return this
        }


        fun applyWithAim(command: Command): Position {
            when (command.type) {
                "forward" -> {
                    pos += command.amount
                    depth += aim * command.amount
                }
                "up" -> aim -= command.amount
                "down" -> aim += command.amount
            }
            return this
        }

        fun ans() = pos * depth

    }

    fun part1(input: Sequence<String>): Int {
        return input
            .map(::Command)
            .fold(Position()) { acc, command -> acc.apply(command) }
            .ans()
    }

    fun part2(input: Sequence<String>): Int {
        return input
            .map(::Command)
            .fold(Position()) { acc, command -> acc.applyWithAim(command) }
            .ans()
    }

    readInput("Day02_test") {
        check(part1(it) == 150)
    }

    readInput("Day02") {
        println(part1(it))
    }

    readInput("Day02_test") {
        check(part2(it) == 900)
    }

    readInput("Day02") {
        println(part2(it))
    }
}
