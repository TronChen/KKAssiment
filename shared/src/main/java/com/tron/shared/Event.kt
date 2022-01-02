package com.tron.shared

open class Event<out T> constructor(
    val status: EventState,
    val data: T? = null,
    val message: String? = null
) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            data
        }
    }

    companion object {
        fun <T> success(data: T?): Event<T> {
            return Event(
                EventState.SUCCESS,
                data,
                null
            )
        }

        @Suppress("UNUSED_PARAMETER")
        fun <T> error(message: String?, data: T? = null): Event<T> {
            return Event(
                EventState.ERROR,
                data,
                message
            )
        }

        fun <T> loading(): Event<T> =
            Event(
                EventState.LOADING,
                null,
                null
            )

        fun <T> init(data: T?): Event<T> {
            return Event(
                EventState.INITIAL,
                data,
                null
            )
        }
    }
}
