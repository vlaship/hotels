package menu

import io.Csv
import io.IoFactory
import io.Loader
import util.inputInt
import java.util.*

class InputMenu(
    private val scanner: Scanner,
    private val factory: IoFactory,
    private var loader: Loader
) {
    fun show(): Loader {
        while (true) {
            println("Input Format menu")
            println("Current format is ${loader.javaClass.simpleName}")
            println("1 - CSV")
            println("0 - return to Main menu")

            when (inputInt("Make a choose:", scanner)) {
                1 -> loader = factory.getLoaderInstance(Csv::class.java)
                0 -> return loader
                else -> println("---")
            }
        }
    }
}