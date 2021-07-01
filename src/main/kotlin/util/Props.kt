package util

import java.util.*

object Props {

    private val props = Properties()

    init {
        props.load(Props.javaClass.classLoader.getResourceAsStream("app.properties"))
    }

    fun get(key: String): String {
        return props[key] as String
    }
}