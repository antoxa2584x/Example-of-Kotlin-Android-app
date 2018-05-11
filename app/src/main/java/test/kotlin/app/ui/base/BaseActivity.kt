package test.kotlin.app.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import test.kotlin.app.ui.base.view.BaseView
import java.util.concurrent.atomic.AtomicLong

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {
    companion object {
        const val KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID"
    }

    private var nextId = AtomicLong(0)
    private var activityId: Long = 0

    override fun getContext(): Context {
        return this
    }

    abstract fun getActivityLayout(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getActivityLayout())

        activityId = savedInstanceState?.getLong(KEY_ACTIVITY_ID) ?: nextId.getAndIncrement()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putLong(KEY_ACTIVITY_ID, activityId)
    }
}