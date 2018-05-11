package test.kotlin.app.core.injection.module

import dagger.Module
import dagger.Provides
import test.kotlin.app.TestApplication
import javax.inject.Singleton

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
@Module
class AppModule(private val testApplication: TestApplication) {
    @Provides
    @Singleton
    fun provideApp() = testApplication
}