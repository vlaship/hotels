package io

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import model.Hotel

class Xml(
    private val mapper: XmlMapper
) : Writer {
    override fun write(hotels: List<Hotel>): String {
        return mapper.writeValueAsString(hotels)
    }
}