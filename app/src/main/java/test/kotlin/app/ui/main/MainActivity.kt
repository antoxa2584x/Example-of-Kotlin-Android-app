package test.kotlin.app.ui.main

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import io.realm.RealmResults
import kotlinx.android.synthetic.main.main_activity_layout.*
import test.kotlin.app.R
import test.kotlin.app.core.extension.OnPostItemClick
import test.kotlin.app.core.extension.app
import test.kotlin.app.core.injection.module.MainActivityModule
import test.kotlin.app.core.model.posts.PostModel
import test.kotlin.app.ui.adapter.PostsAdapter
import test.kotlin.app.ui.base.BaseActivity
import test.kotlin.app.ui.main.presenter.MainPresenter
import test.kotlin.app.ui.main.view.MainView
import javax.inject.Inject

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
class MainActivity : BaseActivity(), MainView {
    override fun getActivityLayout(): Int = R.layout.main_activity_layout

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app.component
                .plus(MainActivityModule())
                .inject(this)

        setUpViews()
        presenter.attachView(this)

        presenter.loadPosts(savedInstanceState?.containsKey(KEY_ACTIVITY_ID) == true)
    }

    private fun setUpViews() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        swipe_refresh_layout.setOnRefreshListener({ presenter.onRefreshCalled() })
    }

    override fun showPosts(posts: RealmResults<PostModel>) {
        recycler_view.adapter ?: run {
            val adapter = PostsAdapter(posts, true)

            adapter.listener = object : OnPostItemClick {
                override fun onItemClick(postModel: PostModel) {
                    presenter.onPostItemClick(postModel)
                }
            }

            recycler_view.adapter = adapter
        }
    }

    override fun showLoadingSpinner() {
        if (!swipe_refresh_layout.isRefreshing)
            swipe_refresh_layout.isRefreshing = true
    }

    override fun dismissLoadingSpinner() {
        if (swipe_refresh_layout.isRefreshing)
            swipe_refresh_layout.isRefreshing = false
    }

    override fun showErrorDialog() =
            AlertDialog.Builder(this)
                    .setTitle(R.string.no_internet_error_title)
                    .setMessage(R.string.no_internet_message)
                    .setPositiveButton(R.string.refrsh, { _, _ -> presenter.onRefreshCalled() })
                    .setNegativeButton(android.R.string.cancel, null)
                    .create()
                    .show()


    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}