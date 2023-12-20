package bangkit.product.chickmed.data.model

import com.google.gson.annotations.SerializedName

data class ErrorModel(
    @field:SerializedName("code")
    val code: Int,
    @field:SerializedName("errors")
    val errors: Map<*, *>,
    @field:SerializedName("message")
    val message: String
)