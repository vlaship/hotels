package io

import model.Hotel
import java.io.File
import java.nio.file.Path

interface Loader {
    fun load(srcPath: File): List<Hotel>
}