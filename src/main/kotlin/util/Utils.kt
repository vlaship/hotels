package util

import java.util.*

fun inputInt(message: String, scanner: Scanner): Int {
    while (true) {
        try {
            println(message)
            return scanner.nextInt()
        } catch (ex: InputMismatchException) {
            println("Need to input a number")
            scanner.nextLine()
        }
    }
}