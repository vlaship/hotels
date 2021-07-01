package io

import model.Hotel

interface Writer {
    fun write(hotels: List<Hotel>): String
}