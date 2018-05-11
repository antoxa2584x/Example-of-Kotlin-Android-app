package test.kotlin.app.core.injection.module

import dagger.Module
import dagger.Provides
import test.kotlin.app.core.api.posts.PostsManger
import test.kotlin.app.core.api.users.UsersManager
import test.kotlin.app.core.injection.scope.ActivityScope
import test.kotlin.app.ui.details.implementation.DetailsPresenterImp
import test.kotlin.app.ui.details.presenter.DetailsPresenter

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
@Module
class DetailsActivityModule {

    @Provides
    @ActivityScope
    fun provideDetailsPresenterImpl(postsManger: PostsManger, usersManager: UsersManager): DetailsPresenter {
        return DetailsPresenterImp(postsManger, usersManager)
    }
}