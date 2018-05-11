package test.kotlin.app

import android.support.test.runner.AndroidJUnit4
import io.realm.Realm
import io.realm.RealmConfiguration
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import test.kotlin.app.core.model.posts.PostModel
import test.kotlin.app.ui.main.implementation.MainPresenterImp


/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */

@RunWith(AndroidJUnit4::class)
class StorageTest {
    private var realm: Realm? = null
    private var mainPresenter: MainPresenterImp? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        val config = RealmConfiguration.Builder().inMemory().name("test-realm").build()
        realm = Realm.getInstance(config)
        mainPresenter = MainPresenterImp(null)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        realm?.close()
    }

    @Test
    @Throws(Exception::class)
    fun checkFakePostSame() {
        val expected = createFakePost(1)
        realm?.executeTransaction { realm1 -> realm1.copyToRealm(expected) }

        val actual = mainPresenter?.getPosts()

        MatcherAssert.assertThat(actual?.get(0)?.id, Matchers.equalTo(expected[0].id))
    }

    @Test
    @Throws(Exception::class)
    fun checkFakePostExist() {
        val expected = createFakePost(1)
        realm?.executeTransaction { realm1 -> realm1.copyToRealm(expected) }

        val actual = mainPresenter?.getPosts()

        Assert.assertNotNull(actual?.get(0))
    }

    @Test
    @Throws(Exception::class)
    fun checkFakePostCountEquals() {
        val count = 10
        val expected = createFakePost(count)
        realm?.executeTransaction { realm1 -> realm1.copyToRealm(expected) }

        val actual = mainPresenter?.getPosts()

        assert(count == actual?.size)
    }

    @Test
    @Throws(Exception::class)
    fun checkFakePostsNotSame() {
        val count = 10
        val expected = createFakePost(count)
        realm?.executeTransaction { realm1 -> realm1.copyToRealm(expected) }

        val actual = mainPresenter?.getPosts()

        assertNotEquals(actual?.get(0), (expected[5]))
    }


    private fun createFakePost(count: Int): List<PostModel> {
        val list = ArrayList<PostModel>()

        (1..count).mapTo(list) {
            PostModel().apply {
                body = "SomePostBody"
                id = it
                userId = 667
                title = "SomePostTitle"
            }
        }
        return list
    }
}