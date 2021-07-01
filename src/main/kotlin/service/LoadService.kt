package service

import io.Loader
import exception.ValidationException
import model.Hotel
import mu.KLogging
import validation.Validator

class LoadService(
    private val loader: Loader,
    private val validator: Validator
) {
    companion object : KLogging()

    fun load(): List<Hotel> {
        val hotels = loader.load()
        return hotels
            .asSequence()
            .filter { isValid(it) }
            .toCollection(arrayListOf())
    }

    private fun isValid(it: Hotel): Boolean = try {
        validator.validate(it)
        true
    } catch (ex: ValidationException) {
        logger.error("Invalid record [{}], reason: [{}]", it.toString(), ex.message)
        false
    }
}