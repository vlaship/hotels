package io

class IoFactory(
    private val loaders: HashMap<Class<*>, Loader>,
    private val writers: HashMap<Class<*>, Writer>
) {

    fun getLoaderInstance(type: Class<*>): Loader {
        return loaders[type]!!
    }

    fun getWriterInstance(type: Class<*>): Writer {
        return writers[type]!!
    }
}