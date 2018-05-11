package test.kotlin.app.core.model.posts

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
@RealmClass
open class CommentModel : RealmModel {
    @SerializedName("postId")
    @Expose
    var postId: Int = 0
    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("name")
    @Expose
    var name: String = ""
    @SerializedName("email")
    @Expose
    var email: String = ""
    @SerializedName("body")
    @Expose
    var body: String = ""

}