package test.kotlin.app.ui.main.presenter

import test.kotlin.app.core.model.posts.PostModel
import test.kotlin.app.ui.base.presenter.BasePresenter
import test.kotlin.app.ui.main.view.MainView

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
interface MainPresenter : BasePresenter<MainView> {
    fun loadPosts(local: Boolean?)
    fun onRefreshCalled()
    fun onPostItemClick(postModel: PostModel)
}