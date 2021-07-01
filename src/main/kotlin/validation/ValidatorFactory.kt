package validation

class ValidatorFactory(
    private val validators: HashMap<Class<*>, Validator>
) {

    fun getValidatorInstance(type: Class<*>): Validator {
        return validators[type]!!
    }
}