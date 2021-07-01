package menu

import io.impl.OpenCsv
import io.impl.JacksonCsv
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
            println("1 - OpenCsv")
            println("2 - JacksonCsv")
            println("0 - return to Main menu")

            when (inputInt("Make a choose:", scanner)) {
                1 -> loader = factory.getLoaderInstance(OpenCsv::class.java)
                2 -> loader = factory.getLoaderInstance(JacksonCsv::class.java)
                0 -> return loader
                else -> println("---")
            }
        }
    }
}