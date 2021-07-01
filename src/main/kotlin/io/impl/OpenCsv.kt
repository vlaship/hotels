package io.impl

import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import io.Loader
import model.Hotel
import mu.KLogging
import util.Props
import java.io.FileReader
import java.lang.NumberFormatException

class OpenCsv : Loader {

    companion object : KLogging()

    override fun load(): List<Hotel> {
        val csvParser = CSVParserBuilder()
            .withSeparator(Props.get("csv-delimiter")[0])
            .build()

        val hotels = mutableListOf<Hotel>()

        val fileReader = FileReader(Props.get("input-path"))
        fileReader.use {
            val reader = CSVReaderBuilder(fileReader)
                .withCSVParser(csvParser)
                .withSkipLines(1)
                .build()

            reader.forEach {
                try {
                    hotels.add(buildHotel(it))
                } catch (ex: NumberFormatException) {
                    logger.error("Invalid record [{}], invalid field stars: [{}]", it.joinToString(), ex.message)
                }
            }
        }
        return hotels
    }

    private fun buildHotel(array: Array<String>): Hotel {
        return Hotel(
            name = array[0],
            address = array[1],
            stars = array[2].toInt(),
            contact = array[3],
            phone = array[4],
            url = array[5]
        )
    }
}