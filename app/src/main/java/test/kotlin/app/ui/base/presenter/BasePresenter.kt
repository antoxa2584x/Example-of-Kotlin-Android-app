package test.kotlin.app.ui.base.presenter

import test.kotlin.app.ui.base.view.BaseView

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
interface BasePresenter<in V : BaseView> {

    fun attachView(view: V)

    fun detachView()

}