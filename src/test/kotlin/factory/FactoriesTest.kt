package factory

import io.impl.JacksonCsv
import io.impl.Json
import io.impl.OpenCsv
import io.impl.Xml
import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

import io.kotest.matchers.types.shouldBeInstanceOf
import validation.impl.DefaultValidator
import java.lang.IllegalArgumentException

internal class FactoriesTest {

    @Test
    fun getLoader() {
        val openCsv = factory.getLoader("opencsv")
        openCsv.shouldBeInstanceOf<OpenCsv>()

        val jacksonCsv = factory.getLoader("jacksoncsv")
        jacksonCsv.shouldBeInstanceOf<JacksonCsv>()

        shouldThrow<IllegalArgumentException> { factory.getLoader("csv") }
    }

    @Test
    fun getWriter() {
        val xml = factory.getWriter("xml")
        xml.shouldBeInstanceOf<Xml>()

        val json = factory.getWriter("json")
        json.shouldBeInstanceOf<Json>()

        shouldThrow<IllegalArgumentException> { factory.getWriter("csv") }
    }

    @Test
    fun getValidator() {
        val default = factory.getValidator("default")
        default.shouldBeInstanceOf<DefaultValidator>()

        shouldThrow<IllegalArgumentException> { factory.getValidator("supervalidator") }
    }
}