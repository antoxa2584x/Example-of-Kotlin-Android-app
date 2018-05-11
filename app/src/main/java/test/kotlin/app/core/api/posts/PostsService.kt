package test.kotlin.app.core.api.posts

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import test.kotlin.app.core.model.posts.CommentModel
import test.kotlin.app.core.model.posts.PostModel

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
interface PostsService {

    @GET("/posts")
    fun getAllPosts(): Observable<Response<ArrayList<PostModel>>>

    /**
     * @param postId - Id of selected post
     */
    @GET("/comments")
    fun getPostComments(@Query("postId") postId: Int): Observable<Response<ArrayList<CommentModel>>>
}