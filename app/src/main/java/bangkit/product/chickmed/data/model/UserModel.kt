package bangkit.product.chickmed.data.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("email")
    val email: String,
    @field:SerializedName("profile")
    val profile: String,
    @field:SerializedName("token")
    val token: String
)