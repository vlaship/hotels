package io

import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import model.Hotel
import mu.KLogging
import util.Props
import java.io.FileReader
import java.lang.NumberFormatException

class Csv : Loader, Writer {

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

    override fun write(hotels: List<Hotel>) {
        TODO("Not yet implemented")
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