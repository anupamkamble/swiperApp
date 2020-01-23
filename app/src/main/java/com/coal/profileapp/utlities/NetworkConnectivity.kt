package com.coal.profileapp.utlities.globals

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject


class NetworkConnectivity @Inject constructor(val context: Context) {

    fun isNetworkConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork?.isConnected ?: false

    }

}