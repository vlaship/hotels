package service

class Converter(
    private val loadService: LoadService,
    private val writeService: WriteService
) {
    fun convert() {
        val hotels = loadService.load()
        writeService.write(hotels)
    }
}