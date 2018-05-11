package test.kotlin.app.ui.main.implementation

import io.realm.Realm
import io.realm.RealmResults
import test.kotlin.app.core.api.base.ApiResultCallback
import test.kotlin.app.core.api.posts.PostsManger
import test.kotlin.app.core.extension.isNetworkConnected
import test.kotlin.app.core.model.posts.PostModel
import test.kotlin.app.ui.base.implementation.BasePresenterImpl
import test.kotlin.app.ui.details.DetailsActivity
import test.kotlin.app.ui.main.presenter.MainPresenter
import test.kotlin.app.ui.main.view.MainView

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
class MainPresenterImp(private val postsManger: PostsManger?) : BasePresenterImpl<MainView>(), MainPresenter, ApiResultCallback {

    override fun attachView(view: MainView) {
        super.attachView(view)
        postsManger?.listener = this
    }

    override fun onPostItemClick(postModel: PostModel) {
        view?.getContext()?.startActivity(
                DetailsActivity.getActivityCallIntent(view?.getContext()!!, postModel.userId, postModel.id)
        )
    }

    override fun onRefreshCalled() {
        loadPosts(false)
    }

    fun getPosts(): RealmResults<PostModel> = Realm.getDefaultInstance().where(PostModel::class.java).findAll()

    override fun loadPosts(local: Boolean?) {
        view?.showPosts(getPosts())

        if (local!!)
            return

        if (!isNetworkConnected(view!!.getContext())) {
            view?.showErrorDialog()
            return
        }

        view?.showLoadingSpinner()
        postsManger?.loadPosts()
    }

    override fun detachView() {
        postsManger?.clear()
        super.detachView()
    }

    override fun onFailure(code: Int) {
        view?.dismissLoadingSpinner()
    }

    override fun onSuccess() {
        view?.dismissLoadingSpinner()
    }
}