package validation.impl

import exception.ValidationException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import model.Hotel
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class DefaultValidatorTest {

    private val testObject = DefaultValidator()

    @Test
    fun `test valid hotel`() {
        // given
        val hotel = Hotel(
            name = "name",
            address = "address",
            stars = 1,
            contact = "contact",
            phone = "phone",
            url = "http://url.com/path/"
        )

        testObject.validate(hotel)
    }

    @Test
    fun `test invalid hotel - invalid stars`() {
        // given
        val hotel = Hotel(
            name = "name",
            address = "address",
            stars = -1,
            contact = "contact",
            phone = "phone",
            url = "http://url.com/path/"
        )

        shouldThrow<ValidationException> { testObject.validate(hotel) }
    }
}