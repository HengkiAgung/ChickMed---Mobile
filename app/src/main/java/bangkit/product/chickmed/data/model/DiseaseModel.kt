package bangkit.product.chickmed.data.model

import com.google.gson.annotations.SerializedName

data class DiseaseModel (
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("description")
    val description: String,
    @field:SerializedName("solution")
    val solution: String,
)