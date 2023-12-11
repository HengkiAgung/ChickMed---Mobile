package com.example.submission1.data.remote.retrofit

import com.example.chickmed.data.model.ArticleModel
import com.example.chickmed.data.model.UserModel
import com.example.chickmed.data.remote.response.TemplateResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    @Headers("Accept: application/json")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("profile") profile: String,
        @Field("password") password: String,
        @Field("confirm_password") confirm_password: String
    ): Response<TemplateResponse<UserModel>>

    @FormUrlEncoded
    @POST("login")
    @Headers("Accept: application/json")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<TemplateResponse<UserModel>>

    @GET("user")
    @Headers("Accept: application/json")
    suspend fun getUser(
        @Header("Authorization") token: String,
    ): Response<TemplateResponse<UserModel>>

    @GET("articles")
    @Headers("Accept: application/json")
    suspend fun getArticles(
        @Query("page") page: Int,
    ): Response<TemplateResponse<List<ArticleModel>>>

    @GET("articles/{id}")
    @Headers("Accept: application/json")
    suspend fun getArticleById(
        @Path("id") id: Int
    ): Response<TemplateResponse<ArticleModel>>

    @Multipart
    @FormUrlEncoded
    @POST("update/user")
    @Headers("Accept: application/json")
    suspend fun updateUser(
        @Header("Authorization") token: String,
        @Field("name") name: String,
        @Field("profile") profile: MultipartBody.Part,
        @Field("email") email: String,
    ): Response<TemplateResponse<UserModel>>
//
//    @Multipart
//    @POST("stories")
//    suspend fun addArticle(
//        @Header("Authorization") token: String,
//        @Part photo: MultipartBody.Part,
//        @Part("description") description: RequestBody,
//        @Part("lat") lat: Float?,
//        @Part("lon") lon: Float?
//    ): Response<MessageResponse>
}