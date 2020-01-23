package com.coal.profileapp.utlities.globals

import android.util.Log


class AppLogger {
    companion object {
        var isEnabled = true
            private set

        fun e(tag: String, msg: String) {
            if (isEnabled) Log.e(tag, msg)
        }

        fun e(tag: String, msg: String, e: Throwable) {
            if (isEnabled) Log.e(tag, msg, e)
        }

        fun w(tag: String, msg: String) {
            if (isEnabled) Log.w(tag, msg)
        }


        fun w(tag: String, msg: String, e: Throwable) {
            if (isEnabled) Log.w(tag, msg, e)
        }

        fun i(tag: String, msg: String) {
            if (isEnabled) Log.i(tag, msg)
        }

        fun d(tag: String, msg: String) {
            if (isEnabled) Log.d(tag, msg)
        }

        fun v(tag: String, msg: String) {
            if (isEnabled) Log.v(tag, msg)
        }
    }
}