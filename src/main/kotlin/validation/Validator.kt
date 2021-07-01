package validation

import model.Hotel

interface Validator {
    fun check(hotel: Hotel)
}