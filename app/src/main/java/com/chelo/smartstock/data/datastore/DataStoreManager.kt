package com.chelo.smartstock.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_prefs")


class DataStoreManager(private val context: Context) {

    private val dataStore = context.dataStore

    val userIdFlow: Flow<Long> = context.dataStore.data.map { prefs ->
        prefs[Keys.USER_ID] ?: -1
    }

    val userNameFlow: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[Keys.USER_NAME] ?: ""
    }

    private object Keys {
        val USER_ID = longPreferencesKey("user_id")
        val USER_NAME = stringPreferencesKey("user_name")
    }

    suspend fun saveUserId(id: Long) {
        context.dataStore.edit { prefs ->
            prefs[Keys.USER_ID] = id
        }
    }

    suspend fun saveUserName(name: String) {
        context.dataStore.edit { prefs ->
            prefs[Keys.USER_NAME] = name
        }
    }


}