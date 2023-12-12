package com.example.chickmed.data.repository

import android.util.Log
import com.example.chickmed.data.model.UserModel
import com.example.chickmed.data.local.preference.UserPreference
import com.example.chickmed.data.remote.response.TemplateResponse
import com.example.chickmed.util.processError
import com.example.submission1.data.remote.retrofit.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class UserRepository(
    private val userPreference: UserPreference,
    private val apiService: ApiService
) {
    fun login(email: String, password: String) = flow {
        val login = apiService.login(email, password)
        if (!login.isSuccessful) {
            val message = login.processError()

            emit(
                TemplateResponse(
                    success = false,
                    message = message,
                    data = UserModel(0, "", "", "", "")
                )
            )
            return@flow
        }

        login.body()?.let {
            saveUser(it.data.id, it.data.email, it.data.name, it.data.profile ?: "", it.data.token)
            emit(it)
        }
    }.catch { e ->
        emit(
            TemplateResponse(
                success = false,
                message = e.message.toString(),
                data = UserModel(0, "", "", "", "")
            )
        )
    }

    fun register(name: String, email: String, password: String, confirm_password: String) = flow {
        val register = apiService.register(name, email, "", password, confirm_password)
        if (!register.isSuccessful) {
            val message = register.processError()

            emit(
                TemplateResponse(
                    success = false,
                    message = message,
                    data = UserModel(0, "", "", "", "")
                )
            )
            return@flow
        }

        register.body()?.apply {
            saveUser(data.id, data.email, data.name, data.profile ?: "", data.token)
            emit(this)
        }
    }.catch { e ->
        emit(
            TemplateResponse(
                success = false,
                message = e.message.toString(),
                data = UserModel(0, "", "", "", "")
            )
        )
    }

    fun getUser(): Flow<TemplateResponse<UserModel>> {
        val userPreference = runBlocking {
            userPreference.getUser().first()
        }

        return flow {
            Log.d("UserRepository", "getUser: ${userPreference.token}")
            val user = apiService.getUser(token = userPreference.token)
            if (!user.isSuccessful) {
                val message = user.processError()
                if (message == "Unauthorized") {
                    logOut()
                }

                emit(
                    TemplateResponse(
                        success = false,
                        message = message,
                        data = UserModel(0, "", "", "", "")
                    )
                )
                return@flow
            }

            user.body()?.apply {
//                saveUser(data.id, data.email, data.name, data.profile ?: "", getToken())
                emit(this)
            }
        }.catch { e ->
            emit(
                TemplateResponse(
                    success = false,
                    message = e.message.toString(),
                    data = UserModel(0, "", "", "", "")
                )
            )
        }
    }

    fun getUserPreference(): Flow<UserModel> = userPreference.getUser()

    suspend fun getToken(): String = userPreference.getUser().first().token

    suspend fun saveUser(id: Int, email: String, name: String, profile: String, token: String) {
        val user = UserModel(id, name, email, profile, token)
        userPreference.saveUser(user)
    }

    suspend fun logOut(): Boolean {
        userPreference.destroyUser()

        return true
    }

    fun updateUser(name: String, profile: File?, email: String) = flow {
        val userPreference = userPreference.getUser().first()

        val multipartPhoto = if (profile != null) MultipartBody.Part.createFormData(
            "profile",
            profile.name,
            profile.asRequestBody("image/jpeg".toMediaType())
        ) else null

        val user = apiService.updateUser(
            token = userPreference.token,
            profile = multipartPhoto,
            name = name,
            email = email,
        )
        if (!user.isSuccessful) {
            val message = user.processError()

            emit(
                TemplateResponse(
                    success = false,
                    message = message,
                    data = UserModel(0, "", "", "", userPreference.token)
                )
            )
            return@flow
        }

        user.body()?.apply {
            saveUser(data.id, data.email, data.name, data.profile ?: "", userPreference.token)
            emit(this)
        }
    }.catch { e ->
        emit(
            TemplateResponse(
                success = false,
                message = e.message.toString(),
                data = UserModel(0, "", "", "", "")
            )
        )
    }

    fun changePassword(newPassword: String, confirmPassword: String) = flow {
        val userPreference = userPreference.getUser().first()
        val user = apiService.changePassword(
            token = userPreference.token,
            password = newPassword,
            confirm_password = confirmPassword
        )
        if (!user.isSuccessful) {
            val message = user.processError()

            emit(
                TemplateResponse(
                    success = false,
                    message = message,
                    data = false
                )
            )
            return@flow
        }

        user.body()?.apply {
            emit(this)
        }
    }.catch { e ->
        emit(
            TemplateResponse(
                success = false,
                message = e.message.toString(),
                data = false
            )
        )
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference, apiService)
            }.also {
                instance = it
            }
    }
}