package test.kotlin.app.core.injection.component

import dagger.Component
import test.kotlin.app.TestApplication
import test.kotlin.app.core.injection.module.*
import javax.inject.Singleton

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
@Singleton
@Component(modules = arrayOf(AppModule::class,
        NetworkingServicesModule::class,
        NetworkingManagersModule::class,
        NetworkingModule::class))
interface AppComponent {
    fun inject(application: TestApplication)
    fun plus(module: MainActivityModule): MainActivityComponent
    fun plus(module: DetailsActivityModule): DetailsActivityComponent
}