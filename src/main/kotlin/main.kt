import io.Csv
import service.LoadService
import validation.DefaultValidator

fun main(args: Array<String>) {
    val loader = Csv()
    val validator = DefaultValidator()
    val loadService = LoadService(loader, validator)
    loadService.load()
}