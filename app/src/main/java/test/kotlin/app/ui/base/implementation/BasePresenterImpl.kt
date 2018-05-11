package test.kotlin.app.ui.base.implementation

import test.kotlin.app.ui.base.presenter.BasePresenter
import test.kotlin.app.ui.base.view.BaseView

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
open class BasePresenterImpl<V : BaseView> : BasePresenter<V> {
    protected var view: V? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }
}