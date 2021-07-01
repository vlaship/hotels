import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.*
import io.impl.JacksonCsv
import io.impl.Json
import io.impl.OpenCsv
import io.impl.Xml
import menu.InputMenu
import menu.MainMenu
import menu.OutputMenu
import menu.ValidatorMenu
import validation.DefaultValidator
import validation.Validator
import validation.ValidatorFactory
import java.util.*

fun main(args: Array<String>) {
    val csv = OpenCsv()
    val mapper = CsvMapper().also {
        it.registerModule(KotlinModule())
    }
    val csvJackson = JacksonCsv(mapper)

    val json = Json(ObjectMapper())
    val xml = Xml(XmlMapper())
    val validator = DefaultValidator()

    val loaders = hashMapOf<Class<*>, Loader>(OpenCsv::class.java to csv, JacksonCsv::class.java to csvJackson)
    val writers = hashMapOf<Class<*>, Writer>(Json::class.java to json, Xml::class.java to xml)
    val validators = hashMapOf<Class<*>, Validator>(DefaultValidator::class.java to validator)

    val scanner = Scanner(System.`in`)
    val inputMenu = InputMenu(scanner, IoFactory(loaders, writers), csv)
    val outputMenu = OutputMenu(scanner, IoFactory(loaders, writers), json)
    val validatorMenu = ValidatorMenu(scanner, ValidatorFactory(validators), validator)

    MainMenu(scanner, inputMenu, outputMenu, validatorMenu, csv, json, validator).show()
}