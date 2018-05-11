package test.kotlin.app.ui.details.implementation

import android.content.Intent
import android.net.Uri
import io.realm.Realm
import io.realm.RealmObjectChangeListener
import io.realm.kotlin.addChangeListener
import test.kotlin.app.core.api.base.ApiResultCallback
import test.kotlin.app.core.api.posts.PostsManger
import test.kotlin.app.core.api.users.UsersManager
import test.kotlin.app.core.extension.isNetworkConnected
import test.kotlin.app.core.model.posts.CommentModel
import test.kotlin.app.core.model.users.UserModel
import test.kotlin.app.ui.base.implementation.BasePresenterImpl
import test.kotlin.app.ui.details.presenter.DetailsPresenter
import test.kotlin.app.ui.details.view.DetailsView
import java.net.URLEncoder


/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
class DetailsPresenterImp(private val postsManger: PostsManger, private val usersManager: UsersManager) :
        BasePresenterImpl<DetailsView>(), DetailsPresenter {
    private var userModel: UserModel? = null

    private var postId = -1
    private var userId = -1

    private var requestCount = 0

    override fun setUserId(id: Int) {
        userId = id
    }

    override fun setPostId(id: Int) {
        postId = id
    }

    private fun getUserAddress(): String = String.format("%s, %s, %s",
            userModel?.address?.city,
            userModel?.address?.street,
            userModel?.address?.suite)

    override fun onUserAddressClick() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(String.format("geo:%s,%s?q=%s,%s(%s)",
                userModel?.address?.geo?.lat, userModel?.address?.geo?.lng,
                userModel?.address?.geo?.lat, userModel?.address?.geo?.lng,
                getUserAddress())))

        view?.getContext()?.startActivity(intent)
    }

    override fun onUserEmailClick() {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto: " + userModel?.email))

        view?.getContext()?.startActivity(Intent.createChooser(intent, "Send email"))
    }

    override fun onUserWebClick() {
        var web = userModel?.website!!

        if (!web.startsWith("https://") && !web.startsWith("http://")) {
            web = "http://" + web
        }

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(web))

        view?.getContext()?.startActivity(intent)
    }

    override fun onUserCompanyClick() {
        val escapedQuery = URLEncoder.encode(userModel?.company?.name, "UTF-8")
        val uri = Uri.parse("http://www.google.com/#q=" + escapedQuery)
        val intent = Intent(Intent.ACTION_VIEW, uri)

        view?.getContext()?.startActivity(intent)
    }

    override fun onUserPhoneClick() {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:" + userModel?.phone)

        view?.getContext()?.startActivity(intent)
    }

    override fun loadUserData(local: Boolean) {
        setUserModelUpdateListener()

        if (local)
            return

        if (!isNetworkConnected(view!!.getContext())) {
            view?.showErrorDialog()
            return
        }

        view?.showLoadingSpinner()
        usersManager.listener = object : ApiResultCallback {
            override fun onSuccess() {
                setUserModelUpdateListener()
                checkRequestCount()
            }

            override fun onFailure(code: Int) {
            }
        }
        usersManager.loadUserData(userId)
    }

    private fun checkRequestCount() {
        if (requestCount == 1) {
            requestCount = 0
            view?.dismissLoadingSpinner()
        } else {
            requestCount++
        }
    }

    private fun setUserModelUpdateListener() {
        userModel = Realm.getDefaultInstance().where(UserModel::class.java).equalTo("id", userId).findFirst()

        userModel?.addChangeListener(RealmObjectChangeListener { t, _ ->
            this.userModel = t
            showUserData(userModel)
        })

        showUserData(userModel)
    }

    private fun showUserData(userModel: UserModel?) {
        val user = userModel ?: return

        view?.apply {
            setUserAddress(getUserAddress())
            setUserCompany(user.company.name)
            setUserCompanyInfo(user.company.catchPhrase)
            setUserPhone(user.phone)
            setUserName(user.name)
            setUserNickname(user.username)
            setUserWeb(user.website)
            setUserEmail(user.email)
            onUserSectionLoaded()
        }
    }

    override fun loadPosComments(local: Boolean) {
        val comments = Realm.getDefaultInstance().where(CommentModel::class.java)
                .equalTo("postId", postId).findAll()

        if (!comments.isEmpty())
            view?.onCommentsSectionLoaded()
        else {
            comments.addChangeListener { _, _ ->
                view?.onCommentsSectionLoaded()
            }
        }

        view?.showComments(comments)

        if (local)
            return

        if (!isNetworkConnected(view!!.getContext())) {
            view?.showErrorDialog()
            return
        }

        view?.showLoadingSpinner()
        postsManger.listener = object : ApiResultCallback {
            override fun onSuccess() {
                checkRequestCount()
            }

            override fun onFailure(code: Int) {
            }
        }
        postsManger.loadComments(postId)
    }

    override fun onRefreshCalled() {
        loadPosComments(false)
        loadUserData(false)
    }

    override fun detachView() {
        postsManger.clear()
        usersManager.clear()

        super.detachView()
    }
}