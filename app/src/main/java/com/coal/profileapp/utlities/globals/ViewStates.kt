package com.coal.profileapp.utlities.globals

data class ViewStates<out T> private constructor(val status: Status, val data: T?) : () -> Any {
    override fun invoke(): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun <T> success(data: T? = null): ViewStates<T> = ViewStates(Status.SUCCESS, data)

        fun <T> error(data: T? = null): ViewStates<T> = ViewStates(Status.ERROR, data)

        fun <T> loading(data: T? = null): ViewStates<T> = ViewStates(Status.LOADING, data)

        fun <T> unknown(data: T? = null): ViewStates<T> = ViewStates(Status.UNKNOWN, data)
    }
}