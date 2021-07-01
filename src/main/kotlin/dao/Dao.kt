package dao

import model.Hotel

interface Dao {
    fun load(): List<Hotel>

    fun write(hotels: List<Hotel>)
}