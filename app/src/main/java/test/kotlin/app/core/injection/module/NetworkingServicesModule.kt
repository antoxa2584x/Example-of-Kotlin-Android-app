package test.kotlin.app.core.injection.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import test.kotlin.app.core.api.posts.PostsService
import test.kotlin.app.core.api.users.UsersService
import javax.inject.Singleton

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
@Module
class NetworkingServicesModule {
    @Provides
    @Singleton
    fun providePostsService(retrofit: Retrofit): PostsService = retrofit.create(PostsService::class.java)

    @Provides
    @Singleton
    fun provideUsersService(retrofit: Retrofit): UsersService = retrofit.create(UsersService::class.java)
}