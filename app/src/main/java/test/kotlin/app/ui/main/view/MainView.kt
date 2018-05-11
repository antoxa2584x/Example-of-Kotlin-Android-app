package test.kotlin.app.ui.main.view

import io.realm.RealmResults
import test.kotlin.app.core.model.posts.PostModel
import test.kotlin.app.ui.base.view.BaseView

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
interface MainView : BaseView {
    fun showPosts(posts: RealmResults<PostModel>)
    fun showLoadingSpinner()
    fun dismissLoadingSpinner()
    fun showErrorDialog()
}