package test.kotlin.app.core.api.users

import io.realm.Realm
import test.kotlin.app.core.api.base.BaseApiManager

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
class UsersManager(private val usersService: UsersService) : BaseApiManager() {

    fun loadUserData(id: Int) {
        usersService.getUser(id)
                .onSuccessDo { userModel ->
                    Realm.getDefaultInstance().executeTransaction { realm ->
                        realm.copyToRealmOrUpdate(userModel)
                    }
                }
    }
}