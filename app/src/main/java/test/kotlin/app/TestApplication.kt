package test.kotlin.app

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import test.kotlin.app.core.injection.component.AppComponent
import test.kotlin.app.core.injection.component.DaggerAppComponent
import test.kotlin.app.core.injection.module.AppModule
import test.kotlin.app.core.injection.module.NetworkingModule


/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
class TestApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .networkingModule(NetworkingModule(BuildConfig.HOST))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)

        Realm.init(this)

        val realmConfiguration = RealmConfiguration.Builder()
                .name("test.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build()

        Realm.setDefaultConfiguration(realmConfiguration)
    }
}