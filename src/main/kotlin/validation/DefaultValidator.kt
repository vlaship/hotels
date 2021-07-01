package validation

import exception.ValidationException
import model.Hotel
import org.valiktor.ConstraintViolationException
import org.valiktor.functions.hasSize
import org.valiktor.functions.isGreaterThan
import org.valiktor.functions.isLessThanOrEqualTo
import org.valiktor.functions.matches
import org.valiktor.validate

const val REGEX = """https?://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"""

class DefaultValidator : Validator {

    override fun check(hotel: Hotel) {
        try {
            validate(hotel) {
                validate(Hotel::name).hasSize(max = 100)
                validate(Hotel::stars).isGreaterThan(0).isLessThanOrEqualTo(5)
                validate(Hotel::url).matches(Regex(REGEX))
            }
        } catch (ex: ConstraintViolationException) {
            val reason = ex.constraintViolations.joinToString { "${it.property}: ${it.constraint.name}" }
            throw ValidationException(reason)
        }
    }
}