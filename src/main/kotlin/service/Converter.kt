package service

import java.io.File

class Converter(
    private val loadService: LoadService,
    private val writeService: WriteService
) {
    fun convert(srcPath: File, dstPath: File) {
        val hotels = loadService.load(srcPath)
        writeService.write(hotels, dstPath)
    }
}