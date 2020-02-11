package com.coal.profileapp.utlities.globals

import timber.log.Timber


object AppLogger {

        var isEnabled = true
            private set

        init { if (isEnabled) Timber.plant(Timber.DebugTree()) }

        fun e( TAG: String, msg: String){ Timber.tag(TAG).e(msg) }
        fun d( TAG: String, msg: String){ Timber.tag(TAG).d(msg) }
        fun i( TAG: String, msg: String){ Timber.tag(TAG).i(msg) }
        fun v( TAG: String, msg: String){ Timber.tag(TAG).v(msg) }
}