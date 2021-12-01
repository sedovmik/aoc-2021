import java.io.File

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String, solver: (Sequence<String>) -> Unit) =
    File("input", "$name.txt").useLines { solver(it) }

