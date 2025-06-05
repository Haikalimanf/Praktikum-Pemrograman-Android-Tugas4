package com.example.loginlogoutprojects

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_preferences")

class DataStoreManager(private val context: Context) {

    private object PreferencesKeys {
        val NAMA = stringPreferencesKey("nama")
        val NO_HP = stringPreferencesKey("no_hp")
        val EMAIL = stringPreferencesKey("email")
    }

    suspend fun saveUserData(nama: String, noHp: String, email: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.NAMA] = nama
            preferences[PreferencesKeys.NO_HP] = noHp
            preferences[PreferencesKeys.EMAIL] = email
        }
    }

    suspend fun clearUserData() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    val userDataFlow: Flow<UserData?> = context.dataStore.data.map { preferences ->
        val nama = preferences[PreferencesKeys.NAMA]
        val noHp = preferences[PreferencesKeys.NO_HP]
        val email = preferences[PreferencesKeys.EMAIL]

        if (nama!=null && noHp!= null && email!= null){
            UserData(nama, noHp, email)
        } else {
            null
        }

    }

}