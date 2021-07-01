package menu

import io.*
import io.impl.Json
import io.impl.Xml
import util.inputInt
import java.util.*

class OutputMenu(
    private val scanner: Scanner,
    private val factory: IoFactory,
    private var writer: Writer
) {
    fun show(): Writer {
        while (true) {
            println("Output Format menu")
            println("Current format is ${writer.javaClass.simpleName}")
            println("1 - JSON")
            println("2 - XML")
            println("0 - return to Main menu")

            when (inputInt("Make a choose:", scanner)) {
                1 -> writer = factory.getWriterInstance(Json::class.java)
                2 -> writer = factory.getWriterInstance(Xml::class.java)
                0 -> return writer
                else -> println("---")
            }
        }
    }
}