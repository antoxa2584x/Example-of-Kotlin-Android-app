package test.kotlin.app.core.injection.module

import dagger.Module
import dagger.Provides
import test.kotlin.app.core.api.posts.PostsManger
import test.kotlin.app.core.api.posts.PostsService
import test.kotlin.app.core.api.users.UsersManager
import test.kotlin.app.core.api.users.UsersService
import javax.inject.Singleton

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
@Module
class NetworkingManagersModule {
    @Provides
    @Singleton
    fun providePostsManager(postsService: PostsService): PostsManger = PostsManger(postsService)

    @Provides
    @Singleton
    fun provideUserManager(usersService: UsersService): UsersManager = UsersManager(usersService)
}