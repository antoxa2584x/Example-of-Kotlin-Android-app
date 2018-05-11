package test.kotlin.app.ui.details.presenter

import test.kotlin.app.ui.base.presenter.BasePresenter
import test.kotlin.app.ui.details.view.DetailsView

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
interface DetailsPresenter : BasePresenter<DetailsView> {
    fun setUserId(id: Int)
    fun setPostId(id: Int)

    fun loadUserData(local: Boolean)
    fun loadPosComments(local: Boolean)
    fun onRefreshCalled()

    fun onUserAddressClick()
    fun onUserEmailClick()
    fun onUserWebClick()
    fun onUserCompanyClick()
    fun onUserPhoneClick()
}