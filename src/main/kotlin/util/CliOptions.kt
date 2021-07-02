package util

import org.apache.commons.cli.*
import kotlin.system.exitProcess

fun parseArgs(args: Array<String>): CommandLine {
    val options = buildOptions()

    return try {
        val cmd = DefaultParser().parse(options, args)
        if (cmd.hasOption("h")) {
            println("Help:")
            println("Input formats: OpenCsv, JacksonCsv\n\tdefault: OpenCsv\n")
            println("Output formats: JacksonJson, JacksonXml\n\tdefault: JacksonJson\n")
            println("Validators: default\n\tdefault: default\n")
            println("Default destination filename is ${Props.get("output-filename-pattern")}")
            exitProcess(0)
        }

        if (!cmd.hasOption("s")) {
            println("Need to point source file")
            printHelp(options)
        }
        cmd
    } catch (exp: ParseException) {
        System.err.println("Invalid arguments. Reason: ${exp.message}")
        printHelp(options)
    }
}

private fun printHelp(options: Options): CommandLine {
    val formatter = HelpFormatter()
    formatter.printHelp("hotels", options)
    exitProcess(0)
}

private fun buildOptions(): Options {
    val options = Options()

    options.addOption("i", "input-format", true, "[REQUIRED] input format")
    options.addOption("o", "output-format", true, "output format")
    options.addOption("s", "source-file", true, "source file")
    options.addOption("d", "destination-file", true, "destination file")
    options.addOption("v", "validator", true, "type of validator")
    options.addOption("h", "help", false, "help")

    return options
}
