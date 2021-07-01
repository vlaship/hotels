package io.impl

import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import io.Loader
import model.Hotel
import mu.KLogging
import util.Props
import java.io.FileReader

class JacksonCsv(
    private val mapper: CsvMapper
) : Loader {

    companion object : KLogging()

    override fun load(): List<Hotel> {
        val readValues = mapper.readerFor(Hotel::class.java)
            .with(CsvSchema.emptySchema().withHeader())
            .readValues<Hotel>(FileReader(Props.get("input-path")))

        val hotels = mutableListOf<Hotel>()

        while (true) {
            try {
                if (!readValues.hasNext()) {
                    break
                }
                hotels.add(readValues.nextValue())
            } catch (ex: InvalidFormatException) {
                OpenCsv.logger.error(ex.message)
            }
        }

        return hotels
    }
}