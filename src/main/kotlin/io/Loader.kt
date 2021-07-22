package io

import model.Hotel
import java.io.File
import java.io.IOException
import java.nio.file.Path

interface Loader {
    fun load(srcPath: File): List<Hotel>
}