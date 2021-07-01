package io

import model.Hotel

interface Loader {
    fun load(): List<Hotel>
}