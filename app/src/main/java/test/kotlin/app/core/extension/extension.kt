package test.kotlin.app.core.extension

import android.content.Context
import android.net.ConnectivityManager


/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
fun isNetworkConnected(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}