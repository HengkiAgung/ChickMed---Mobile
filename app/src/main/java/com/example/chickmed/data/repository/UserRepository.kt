package com.example.chickmed.data.repository

import com.example.chickmed.data.model.UserModel
import com.example.chickmed.data.local.preference.UserPreference
import kotlinx.coroutines.flow.Flow

class UserRepository (
    private val userPreference: UserPreference
) {
    fun getUser(): Flow<UserModel> = userPreference.getUser()

    suspend fun saveUser(id: Int, email: String, name: String, profile: String, token: String) {
        val user = UserModel(id, name, email, profile, token)
        userPreference.saveUser(user)
    }

    suspend fun destroyUser() = userPreference.destroyUser()

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference)
            }.also {
                instance = it }
    }
}