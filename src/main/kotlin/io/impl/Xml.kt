package io.impl

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import io.Writer
import model.Hotel

class Xml(
    private val mapper: XmlMapper
) : Writer {
    override fun write(hotels: List<Hotel>): String {
        return mapper.writeValueAsString(hotels)
    }
}