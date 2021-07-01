package validation

import exception.ValidationException
import model.Hotel
import org.valiktor.ConstraintViolationException
import org.valiktor.functions.*
import org.valiktor.validate

const val REGEX_URL = """https?://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"""

class DefaultValidator : Validator {

    override fun validate(hotel: Hotel) {
        try {
            validate(hotel) {
                validate(Hotel::name).hasSize(max = 100)
                validate(Hotel::stars).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(5)
                validate(Hotel::url).matches(Regex(REGEX_URL))
            }
        } catch (ex: ConstraintViolationException) {
            val reason = ex.constraintViolations.joinToString { "${it.property}: ${it.constraint.name}" }
            throw ValidationException(reason)
        }
    }
}