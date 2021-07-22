package io.impl

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.nio.file.Path

internal class OpenCsvTest {

    private val testObject = factory.getLoader("openCsv")

    @Test
    fun `load csv`() {
        val csv = Path.of("src/test/resources", "hotels.csv").toFile()
        val result = testObject.load(csv)
        result.size shouldBe 5
    }
}