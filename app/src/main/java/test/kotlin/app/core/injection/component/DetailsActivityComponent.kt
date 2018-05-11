package test.kotlin.app.core.injection.component

import dagger.Subcomponent
import test.kotlin.app.core.injection.module.DetailsActivityModule
import test.kotlin.app.core.injection.scope.ActivityScope
import test.kotlin.app.ui.details.DetailsActivity

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
@ActivityScope
@Subcomponent(modules = arrayOf(DetailsActivityModule::class))
interface DetailsActivityComponent {
    fun inject(detailsActivity: DetailsActivity)
}