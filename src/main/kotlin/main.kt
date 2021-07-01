import dao.CsvDao
import validation.DefaultValidator

fun main(args: Array<String>) {
    val csv = CsvDao(DefaultValidator())

    csv.load()
}