package com.example.chickmed.data.model

import com.google.gson.annotations.SerializedName

data class ReportModel(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("date")
    val date: String,
    @field:SerializedName("raw_image")
    val raw_image: String,
    @field:SerializedName("result_image")
    val result_image: String,
    @field:SerializedName("report_disease")
    val report_disease: List<ReportDisease>,
)