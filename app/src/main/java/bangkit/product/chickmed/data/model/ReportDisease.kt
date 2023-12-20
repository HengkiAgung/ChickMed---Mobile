package bangkit.product.chickmed.data.model

import com.google.gson.annotations.SerializedName

data class ReportDisease(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("confidence")
    val confidence: String,
    @field:SerializedName("diseases")
    val diseases: DiseaseModel,
)
