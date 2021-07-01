package io.impl

import com.fasterxml.jackson.databind.ObjectMapper
import io.Writer
import model.Hotel

class Json(
    private val mapper: ObjectMapper
) : Writer {
    override fun write(hotels: List<Hotel>): String {
        return mapper.writeValueAsString(hotels)
    }
}