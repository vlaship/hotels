package io.impl

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.nio.file.Path

internal class JacksonCsvTest {

    private val testObject = factory.getLoader("jacksonCsv")

    @Test
    fun `load csv`() {
        val csv = Path.of("src/test/resources", "hotels.csv").toFile()
        val result = testObject.load(csv)
        result.size shouldBe 5
    }
}