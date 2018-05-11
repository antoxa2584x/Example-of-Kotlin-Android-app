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
open class AddressModel : RealmModel {
    @SerializedName("street")
    @Expose
    var street: String = ""
    @SerializedName("suite")
    @Expose
    var suite: String = ""
    @SerializedName("city")
    @Expose
    var city: String = ""
    @SerializedName("zipcode")
    @Expose
    var zipcode: String = ""
    @SerializedName("geo")
    @Expose
    var geo: GeoModel = GeoModel()
}