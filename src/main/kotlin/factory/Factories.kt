package factory

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.Loader
import io.Writer
import io.impl.JacksonCsv
import io.impl.Json
import io.impl.OpenCsv
import io.impl.Xml
import validation.Validator
import validation.impl.DefaultValidator

fun getLoader(arg: String): Loader {
    return when (InputFormat.valueOf(arg.uppercase())) {
        InputFormat.OPENCSV -> OpenCsv()
        InputFormat.JACKSONCSV -> JacksonCsv(CsvMapper().also { it.registerModule(KotlinModule()) })
    }
}

fun getWriter(arg: String): Writer {
    return when (OutputFormat.valueOf(arg.uppercase())) {
        OutputFormat.JSON -> Json(ObjectMapper())
        OutputFormat.XML -> Xml(XmlMapper())
    }
}

fun getValidator(arg: String): Validator {
    return when (ValidatorType.valueOf(arg.uppercase())) {
        ValidatorType.DEFAULT -> DefaultValidator()
    }
}
