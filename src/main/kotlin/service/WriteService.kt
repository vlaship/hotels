package service

import io.Writer
import model.Hotel

class WriteService(
    private val writer: Writer
) {
    fun write(hotels: List<Hotel>) {
        writer.write(hotels)
    }
}