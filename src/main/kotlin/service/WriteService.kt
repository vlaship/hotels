package service

import io.Writer
import model.Hotel
import mu.KLogging
import util.Props
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val FILENAME_PATTERN = "yyyyMMdd_HHmmss"

class WriteService(
    private val writer: Writer
) {
    companion object : KLogging()

    fun write(hotels: List<Hotel>) {
        val str = listOf(writer.write(hotels))
        val filename = LocalDateTime.now().format(DateTimeFormatter.ofPattern(FILENAME_PATTERN))
        val ext = writer::class.java.simpleName
        val path = Path.of(Props.get("output-path"))
        try {
            if (Files.notExists(path)) {
                Files.createDirectories(path)
            }
            Files.write(Path.of(path.toString(), "$filename.$ext"), str)
            println("File file [$filename.$ext] was saved successfully")
        } catch (ex: IOException) {
            logger.error("Error [{}] while saving file [$filename.$ext]", ex.message, ex)
        }
    }
}