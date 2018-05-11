package test.kotlin.app.core.extension

import test.kotlin.app.core.model.posts.PostModel

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
interface OnPostItemClick {
    fun onItemClick(postModel: PostModel)
}