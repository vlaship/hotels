package validation.impl

import exception.ValidationException
import io.kotest.assertions.throwables.shouldThrow
import model.Hotel
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
    fun `test invalid hotel - invalid stars less 0`() {
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

    @Test
    fun `test invalid hotel - invalid stars more 5`() {
        // given
        val hotel = Hotel(
            name = "name",
            address = "address",
            stars = 6,
            contact = "contact",
            phone = "phone",
            url = "http://url.com/path/"
        )

        shouldThrow<ValidationException> { testObject.validate(hotel) }
    }

    @Test
    fun `test invalid hotel - invalid name more 100`() {
        val builder = StringBuilder()
        for (i in 1..21) {
            builder.append("name1")
        }
        // given
        val hotel = Hotel(
            name = builder.toString(),
            address = "address",
            stars = 5,
            contact = "contact",
            phone = "phone",
            url = "http://url.com/path/"
        )

        shouldThrow<ValidationException> { testObject.validate(hotel) }
    }

    @Test
    fun `test invalid hotel - invalid url`() {
        // given
        val hotel = Hotel(
            name = "name",
            address = "address",
            stars = 5,
            contact = "contact",
            phone = "phone",
            url = "http//url.com/path/"
        )

        shouldThrow<ValidationException> { testObject.validate(hotel) }
    }
}