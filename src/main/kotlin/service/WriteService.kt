package service

import io.Writer
import model.Hotel
import mu.KLogging
import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets

class WriteService(
    private val writer: Writer
) {
    companion object : KLogging()

    fun write(hotels: List<Hotel>, dstPath: File) {
        logger.info("Writing file [{}]", dstPath)
        val str = listOf(writer.write(hotels))
        try {
            dstPath.writeText(str.joinToString("\n"), StandardCharsets.UTF_8)
            println("File file [$dstPath] was saved successfully")
        } catch (ex: IOException) {
            logger.error("Error [{}] while saving file [$dstPath]", ex.message, ex)
        }
    }
}