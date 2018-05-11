package test.kotlin.app.core.injection.module

import dagger.Module
import dagger.Provides
import test.kotlin.app.core.api.posts.PostsManger
import test.kotlin.app.core.injection.scope.ActivityScope
import test.kotlin.app.ui.main.implementation.MainPresenterImp
import test.kotlin.app.ui.main.presenter.MainPresenter

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
@Module
class MainActivityModule {

    @Provides
    @ActivityScope
    fun provideDetailsPresenterImpl(postsManger: PostsManger): MainPresenter {
        return MainPresenterImp(postsManger)
    }
}