package menu

import util.inputInt
import validation.DefaultValidator
import validation.Validator
import validation.ValidatorFactory
import java.util.*

class ValidatorMenu(
    private val scanner: Scanner,
    private val factory: ValidatorFactory,
    private var validator: Validator
) {
    fun show(): Validator {
        while (true) {
            println("Output Format menu")
            println("Current format is ${validator.javaClass.simpleName}")
            println("1 - Default")
            println("0 - return to Main menu")

            when (inputInt("Make a choose:", scanner)) {
                1 -> validator = factory.getValidatorInstance(DefaultValidator::class.java)
                0 -> return validator
                else -> println("---")
            }
        }
    }
}