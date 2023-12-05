package com.example.chickmed.data.model

data class ReportModel(
    val id: Int,
    val date: String,
    val resultImage: String,
    val diseases: List<DiseaseModel>,
)