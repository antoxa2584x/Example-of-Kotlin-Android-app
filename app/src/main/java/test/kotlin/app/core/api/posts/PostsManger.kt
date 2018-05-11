package test.kotlin.app.core.api.posts

import io.realm.Realm
import test.kotlin.app.core.api.base.BaseApiManager

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
class PostsManger(private val postsService: PostsService) : BaseApiManager() {

    fun loadPosts() {
        postsService.getAllPosts()
                .onSuccessDo {
                    Realm.getDefaultInstance().executeTransactionAsync { realm ->
                        realm.copyToRealmOrUpdate(it)
                    }
                }
    }

    fun loadComments(postId: Int) {
        postsService.getPostComments(postId)
                .onSuccessDo {
                    Realm.getDefaultInstance().executeTransactionAsync { realm ->
                        realm.copyToRealmOrUpdate(it)
                    }
                }
    }
}