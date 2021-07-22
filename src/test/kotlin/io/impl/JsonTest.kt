package io.impl

import com.jayway.jsonpath.JsonPath
import io.kotest.matchers.shouldBe
import model.Hotel
import org.junit.jupiter.api.Test

internal class JsonTest {

    private val testObject = factory.getWriter("json")

    @Test
    fun `load csv`() {
        val hotels = listOf(
            Hotel("name0", "address0", 0, "contact0", "phone0", "url0"),
            Hotel("name1", "address1", 1, "contact1", "phone1", "url1"),
            Hotel("name2", "address2", 2, "contact2", "phone2", "url2")
        )
        val result = testObject.write(hotels)

        JsonPath.read<String>(result, "\$[1].name") shouldBe "name1"
        JsonPath.read<String>(result, "\$[2].url") shouldBe "url2"
        JsonPath.read<Int>(result, "\$[1].stars") shouldBe 1
        JsonPath.read<String>(result, "\$[0].contact") shouldBe "contact0"
    }

}