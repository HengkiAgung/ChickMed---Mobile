package com.example.chickmed.data.remote.retrofit

import com.example.chickmed.data.model.UserModel
import com.example.chickmed.data.remote.response.MessageResponse
import com.example.chickmed.data.remote.response.TemplateResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<TemplateResponse<UserModel>>

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<TemplateResponse<UserModel>>
}