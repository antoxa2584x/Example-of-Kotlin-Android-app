package test.kotlin.app.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import io.realm.RealmResults
import kotlinx.android.synthetic.main.details_activity_layout.*
import kotlinx.android.synthetic.main.user_info_layout.*
import test.kotlin.app.R
import test.kotlin.app.core.extension.app
import test.kotlin.app.core.injection.module.DetailsActivityModule
import test.kotlin.app.core.model.posts.CommentModel
import test.kotlin.app.ui.adapter.CommentsAdapter
import test.kotlin.app.ui.base.BaseActivity
import test.kotlin.app.ui.details.presenter.DetailsPresenter
import test.kotlin.app.ui.details.view.DetailsView
import javax.inject.Inject

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
class DetailsActivity : BaseActivity(), DetailsView, View.OnClickListener {
    companion object {
        const val USER_ID = "user_id"
        const val POST_ID = "post_id"

        fun getActivityCallIntent(context: Context, userId: Int?, postId: Int?): Intent =
                Intent(context, DetailsActivity::class.java).putExtra(USER_ID, userId).putExtra(POST_ID, postId)
    }


    override fun getActivityLayout(): Int = R.layout.details_activity_layout

    @Inject
    lateinit var presenter: DetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        app.component
                .plus(DetailsActivityModule())
                .inject(this)

        setUpView()
        presenter.attachView(this)

        presenter.setPostId(intent.getIntExtra(POST_ID, -1))
        presenter.setUserId(intent.getIntExtra(USER_ID, -1))

        val local = savedInstanceState?.containsKey(KEY_ACTIVITY_ID) == true
        presenter.loadUserData(local)
        presenter.loadPosComments(local)

    }

    private fun setUpView() {
        user_address_layout.setOnClickListener(this)
        user_email_layout.setOnClickListener(this)
        user_phone_layout.setOnClickListener(this)
        user_company_layout.setOnClickListener(this)
        user_web_address.setOnClickListener(this)

        recycler_view.isNestedScrollingEnabled = false
        recycler_view.layoutManager = LinearLayoutManager(this)

        swipe_refresh_layout.setOnRefreshListener({ presenter.onRefreshCalled() })
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.user_address_layout -> presenter.onUserAddressClick()
            R.id.user_email_layout -> presenter.onUserEmailClick()
            R.id.user_phone_layout -> presenter.onUserPhoneClick()
            R.id.user_company_layout -> presenter.onUserCompanyClick()
            R.id.user_web_address -> presenter.onUserWebClick()
        }
    }

    override fun onUserSectionLoaded() {
        user_section_layout.visibility = View.VISIBLE
    }

    override fun onCommentsSectionLoaded() {
        comments_section_layout.visibility = View.VISIBLE
    }

    override fun setUserName(name: String) {
        user_name.text = name
    }

    override fun setUserNickname(nick: String) {
        user_nickname.text = nick
    }

    override fun setUserPhone(phone: String) {
        user_phone.text = phone
    }

    override fun setUserCompany(company: String) {
        user_company_name.text = company
    }

    override fun setUserAddress(address: String) {
        user_address.text = address
    }

    override fun setUserWeb(web: String) {
        user_web_address.text = web
    }

    override fun setUserCompanyInfo(companyInfo: String) {
        user_company_catch_phase.text = companyInfo
    }

    override fun setUserEmail(email: String) {
        user_email.text = email
    }

    override fun showComments(comments: RealmResults<CommentModel>) {
        recycler_view.adapter ?: run {
            recycler_view.adapter = CommentsAdapter(comments, true)
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

    private var errorDialog: AlertDialog? = null

    override fun showErrorDialog() {
        errorDialog?.let {
            if (errorDialog?.isShowing!!)
                errorDialog?.show()
        } ?: run {
            errorDialog = AlertDialog.Builder(this)
                    .setTitle(R.string.no_internet_error_title)
                    .setMessage(R.string.no_internet_message)
                    .setPositiveButton(R.string.refrsh, { _, _ -> presenter.onRefreshCalled() })
                    .setNegativeButton(android.R.string.cancel, null)
                    .create()

            errorDialog?.show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}