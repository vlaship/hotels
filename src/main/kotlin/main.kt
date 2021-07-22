import com.fasterxml.jackson.databind.ObjectMapper
import factory.getLoader
import factory.getValidator
import factory.getWriter
import io.Loader
import io.Writer
import io.impl.Json
import io.impl.OpenCsv
import org.apache.commons.cli.CommandLine
import service.Converter
import service.LoadService
import service.WriteService
import util.Props
import util.parseArgs
import validation.Validator
import validation.impl.DefaultValidator
import java.io.File
import java.nio.file.Path
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.system.exitProcess

fun main(args: Array<String>) {

    val cmd = parseArgs(args)

    val loader: Loader
    val writer: Writer
    val validator: Validator

    try {
        loader = getLoader(cmd)
        writer = getWriter(cmd)
        validator = getValidator(cmd)
    } catch (ex: IllegalArgumentException) {
        System.err.println("Invalid format or validator type")
        exitProcess(0)
    }

    val srcPath = Path.of(cmd.getOptionValue("s")).toFile()
    if (!srcPath.exists()) {
        System.err.println("Source file [$srcPath] not found")
    }

    val dstPath = getDestinationFile(cmd, writer)

    val loadService = LoadService(loader, validator)
    val writeService = WriteService(writer)
    Converter(loadService, writeService).convert(srcPath, dstPath)
}

private fun getDestinationFile(cmd: CommandLine, writer: Writer): File {
    return if (cmd.hasOption("d")) {
        Path.of(cmd.getOptionValue("d")).toFile()
    } else {
        destinationFile(writer)
    }
}

private fun getValidator(cmd: CommandLine): Validator {
    return if (cmd.hasOption("v")) {
        getValidator(cmd.getOptionValue("v"))
    } else {
        DefaultValidator()
    }
}

private fun getWriter(cmd: CommandLine): Writer {
    return if (cmd.hasOption("o")) {
        getWriter(cmd.getOptionValue("o"))
    } else {
        Json(ObjectMapper())
    }
}

private fun getLoader(cmd: CommandLine): Loader {
    return if (cmd.hasOption("i")) {
        getLoader(cmd.getOptionValue("i"))
    } else {
        OpenCsv()
    }
}

private fun destinationFile(writer: Writer): File {
    val format = LocalDateTime.now().format(DateTimeFormatter.ofPattern(Props.get("output-filename-pattern")))
    return Path.of("$format.${writer.javaClass.simpleName}").toFile()
}
