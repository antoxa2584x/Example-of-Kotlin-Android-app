package test.kotlin.app.ui.details.view

import io.realm.RealmResults
import test.kotlin.app.core.model.posts.CommentModel
import test.kotlin.app.ui.base.view.BaseView

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
interface DetailsView : BaseView { //Can implement MainView, but no
    fun showLoadingSpinner()
    fun dismissLoadingSpinner()
    fun showErrorDialog()

    fun onUserSectionLoaded()
    fun onCommentsSectionLoaded()

    fun setUserName(name: String)
    fun setUserNickname(nick: String)
    fun setUserPhone(phone: String)
    fun setUserCompany(company: String)
    fun setUserCompanyInfo(companyInfo: String)
    fun setUserAddress(address: String)
    fun setUserWeb(web: String)
    fun setUserEmail(email: String)

    fun showComments(comments: RealmResults<CommentModel>)
}