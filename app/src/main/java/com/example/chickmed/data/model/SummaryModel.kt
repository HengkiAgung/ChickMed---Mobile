package com.example.chickmed.data.model

import com.google.gson.annotations.SerializedName

data class SummaryModel (
    @field:SerializedName("total")
    var total: Int,
    @field:SerializedName("sickChick")
    var sickChick: Int,
    @field:SerializedName("healthyChick")
    var healthyChick: Int,
)