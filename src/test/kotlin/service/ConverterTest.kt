package service

import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import model.Hotel
import org.junit.jupiter.api.Test
import java.io.File

internal class ConverterTest {
    @Test
    fun `test converting`() {
        val hotel = Hotel(
            name = "name",
            address = "address",
            stars = 1,
            contact = "contact",
            phone = "phone",
            url = "http://url.com/path/"
        )
        val file = mockk<File>()
        val loadService = mockk<LoadService>()
        val writeService = mockk<WriteService>()
        every { loadService.load(any<File>()) } returns listOf(hotel)
        justRun { writeService.write(any<List<Hotel>>(), any<File>()) }

        val testObject = Converter(loadService, writeService)
        testObject.convert(file, file)

        verify { loadService.load(any()) }
        verify { writeService.write(any(), any()) }
    }
}