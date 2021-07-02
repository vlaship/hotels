package menu

import io.Loader
import io.Writer
import service.Converter
import service.LoadService
import service.WriteService
import util.inputInt
import validation.Validator
import validation.impl.DefaultValidator
import java.util.*

class MainMenu(
    private val scanner: Scanner,
    private val inputMenu: InputMenu,
    private val outputMenu: OutputMenu,
    private val validatorMenu: ValidatorMenu,
    private var loader: Loader,
    private var writer: Writer,
    private var validator: Validator = DefaultValidator()
) {

    fun show() {
        while (true) {
            println("Main menu")
            println("1 - input format")
            println("2 - output format")
            println("3 - validator")
            println("9 - convert")
            println("0 - exit")

            when (inputInt("Make a choose:", scanner)) {
                1 -> loader = inputMenu.show()
                2 -> writer = outputMenu.show()
                3 -> validator = validatorMenu.show()
                9 -> convert()
                0 -> return
                else -> println("---")
            }
        }
    }

    private fun convert() {
        val loadService = LoadService(loader, validator)
        val writeService = WriteService(writer)
        Converter(loadService, writeService).convert()
    }
}