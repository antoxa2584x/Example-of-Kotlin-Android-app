package test.kotlin.app.core.api.users

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import test.kotlin.app.core.model.users.UserModel

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
interface UsersService {
    /**
     * @param userId Id of selected user
     */
    @GET("/users/{user_id}")
    fun getUser(@Path("user_id") userId: Int): Observable<Response<UserModel>>
}