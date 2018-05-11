package test.kotlin.app.core.extension

import android.support.v7.app.AppCompatActivity
import test.kotlin.app.TestApplication

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
val AppCompatActivity.app: TestApplication
    get() = application as TestApplication