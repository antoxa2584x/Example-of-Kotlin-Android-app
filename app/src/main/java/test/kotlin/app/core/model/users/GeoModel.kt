package test.kotlin.app.core.model.users

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmModel
import io.realm.annotations.RealmClass


/**
 * Created by Anton. A on 13.03.2018.
 * Version 1.0
 */
@RealmClass
open class GeoModel : RealmModel {
    @SerializedName("lat")
    @Expose
    var lat: String = ""
    @SerializedName("lng")
    @Expose
    var lng: String = ""
}