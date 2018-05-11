package test.kotlin.app.core.injection.component

import dagger.Subcomponent
import test.kotlin.app.core.injection.module.MainActivityModule
import test.kotlin.app.core.injection.scope.ActivityScope
import test.kotlin.app.ui.main.MainActivity

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
@ActivityScope
@Subcomponent(modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent {
    fun inject(mainActivity: MainActivity)
}