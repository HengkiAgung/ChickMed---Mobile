package com.product.submission1.data.remote.retrofit

import bangkit.product.chickmed.data.model.ArticleModel
import bangkit.product.chickmed.data.model.ReportModel
import bangkit.product.chickmed.data.model.SummaryModel
import bangkit.product.chickmed.data.model.UserModel
import bangkit.product.chickmed.data.remote.response.TemplateResponse
import okhttp3.MultipartBody
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
    @POST("predict")
    @Headers("Accept: application/json")
    suspend fun doAnalysis(
        @Header("Authorization") token: String,
        @Part image: MultipartBody.Part?,
        @Part("user_id") user_id: Int,
    ): Response<Any>

    @GET("reports")
    @Headers("Accept: application/json")
    suspend fun getReports(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
    ): Response<TemplateResponse<List<ReportModel>>>

    @GET("reports/detail/{id}")
    @Headers("Accept: application/json")
    suspend fun getReportById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<TemplateResponse<ReportModel>>

    @GET("summary")
    @Headers("Accept: application/json")
    suspend fun getSummary(
        @Header("Authorization") token: String,
    ): Response<TemplateResponse<SummaryModel>>

    @Multipart
    @POST("update/user")
    @Headers("Accept: application/json")
    suspend fun updateUser(
        @Header("Authorization") token: String,
        @Part("name") name: String,
        @Part profile: MultipartBody.Part?,
        @Part("email") email: String,
    ): Response<TemplateResponse<UserModel>>

    @FormUrlEncoded
    @POST("change/password")
    @Headers("Accept: application/json")
    suspend fun changePassword(
        @Header("Authorization") token: String,
        @Field("password") password: String,
        @Field("confirm_password") confirm_password: String,
    ): Response<TemplateResponse<Boolean>>

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