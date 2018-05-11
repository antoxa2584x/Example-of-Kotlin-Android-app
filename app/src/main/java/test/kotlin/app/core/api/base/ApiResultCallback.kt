package test.kotlin.app.core.api.base

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
interface ApiResultCallback {
    fun onSuccess()
    fun onFailure(code: Int)
}