package io.impl

import com.jayway.jsonpath.JsonPath
import io.kotest.matchers.shouldBe
import model.Hotel
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Files
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory
import kotlin.io.path.writeText


internal class XmlTest {

    @TempDir
    lateinit var tempDir: Path

    private val testObject = factory.getWriter("xml")

    @Test
    fun `load csv`() {
        val hotels = listOf(
            Hotel("name0", "address0", 0, "contact0", "phone0", "url0"),
            Hotel("name1", "address1", 1, "contact1", "phone1", "url1"),
            Hotel("name2", "address2", 2, "contact2", "phone2", "url2")
        )
        val result = testObject.write(hotels)
        val tempFile = Files.createTempFile(tempDir, "", "")
        tempFile.writeText(result)

        val xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(tempFile.toFile())
        val xPath = XPathFactory.newInstance().newXPath()

        val evaluate = xPath.compile("/ArrayList/item[0]/name/text()").evaluate(xmlDocument, XPathConstants.STRING)

        print(evaluate)

    }
}