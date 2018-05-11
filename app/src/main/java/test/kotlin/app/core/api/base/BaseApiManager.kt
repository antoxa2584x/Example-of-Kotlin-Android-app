package test.kotlin.app.core.api.base

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
abstract class BaseApiManager {
    private val disposable: CompositeDisposable by lazy { CompositeDisposable() }

    var listener: ApiResultCallback? = null

    fun clear() {
        disposable.clear()
    }

    protected fun <T> Observable<Response<T>>.onSuccessDo(block: (T) -> Unit) {
        this.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.isSuccessful) {
                        val body = it.body() ?: return@subscribe
                        block(body)
                        listener?.onSuccess()
                    } else {
                        listener?.onFailure(it.code())
                    }
                }.also {
                    disposable.add(it)
                }
    }
}