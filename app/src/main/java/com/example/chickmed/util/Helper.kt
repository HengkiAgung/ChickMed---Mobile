package com.example.chickmed.util

import com.example.chickmed.data.model.ErrorModel
import com.google.gson.Gson
import retrofit2.Response

fun Response<*>.processError(): String {
    when (this.code()) {
        400 -> {
            return "Bad request"
        }

        401 -> {
            return "Unauthorized"
        }

        403 -> {
            return "Forbidden"
        }

        404 -> {
            return "Not found"
        }

        500 -> {
            return "Internal server error"
        }
        else -> {
            val errorBody = this.errorBody()?.string()
            return if (!errorBody.isNullOrBlank()) {
                val gson = Gson()
                val errorResponse = gson.fromJson(errorBody, ErrorModel::class.java)
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

    }
}