package com.example.chickmed.data.local.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.chickmed.data.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")
class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {
    private val token = stringPreferencesKey("token")
    private val id = stringPreferencesKey("id")
    private val name = stringPreferencesKey("name")

    suspend fun saveUser(user: UserModel) {
        dataStore.edit {
            it[id] = user.id
            it[name] = user.name
            it[token] = user.token
        }
    }

    fun getUser(): Flow<UserModel> = dataStore.data.map {
        UserModel(
            it[id] ?: "",
            it[name] ?: "",
            it[token] ?: ""
        )
    }

    suspend fun destroyUser() = dataStore.edit { it.clear() }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}