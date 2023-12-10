package com.example.chickmed.util

import com.example.chickmed.data.model.ErrorModel
import com.google.gson.Gson

fun String?.processError(): String {
    return if (!this.isNullOrBlank()) {
        val gson = Gson()
        val errorResponse = gson.fromJson(this, ErrorModel::class.java)
        try {
            errorResponse.errors.map {
                (it.value as List<*>)[0]
            }.joinToString("\n")
        } catch (e: Exception) {
            errorResponse.message
        }
    } else {
        "Something went wrong"
    }
}