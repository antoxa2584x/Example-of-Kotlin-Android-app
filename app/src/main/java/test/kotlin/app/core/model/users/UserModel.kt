package test.kotlin.app.core.model.users

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
open class UserModel : RealmModel {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("name")
    @Expose
    var name: String = ""
    @SerializedName("username")
    @Expose
    var username: String = ""
    @SerializedName("email")
    @Expose
    var email: String = ""
    @SerializedName("address")
    @Expose
    var address: AddressModel = AddressModel()
    @SerializedName("phone")
    @Expose
    var phone: String = ""
    @SerializedName("website")
    @Expose
    var website: String = ""
    @SerializedName("company")
    @Expose
    var company: CompanyModel = CompanyModel()

}