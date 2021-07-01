package validation

import model.Hotel

interface Validator {
    fun validate(hotel: Hotel)
}