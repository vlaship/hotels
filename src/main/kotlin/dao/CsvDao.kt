package dao

import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import exception.ValidationException
import model.Hotel
import mu.KLogging
import util.Props
import validation.Validator
import java.io.FileReader
import java.lang.NumberFormatException

class CsvDao(private val validator: Validator) : Dao {

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
                } catch (ex: ValidationException) {
                    logger.error("Invalid record [{}], reason: [{}]", it.joinToString(), ex.message)
                }
            }
        }
        return hotels
    }

    override fun write(hotels: List<Hotel>) {
        TODO("Not yet implemented")
    }

    private fun buildHotel(array: Array<String>): Hotel {
        val hotel = Hotel(
            name = array[0],
            address = array[1],
            stars = parseStars(array[2]),
            contact = array[3],
            phone = array[4],
            url = array[5]
        )
        validator.check(hotel)
        return hotel
    }

    private fun parseStars(str: String): Int {
        try {
            return str.toInt()
        } catch (ex: NumberFormatException) {
            throw ValidationException("invalid field stars: ${ex.message}")
        }
    }
}