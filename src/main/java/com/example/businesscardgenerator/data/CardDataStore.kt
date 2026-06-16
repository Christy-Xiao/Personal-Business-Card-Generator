package com.example.businesscardgenerator.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "business_card_prefs")

class CardDataStore(private val context: Context) {

    companion object {
        private val FULL_NAME_KEY = stringPreferencesKey("full_name")
        private val POSITION_KEY = stringPreferencesKey("position")
        private val PHONE_KEY = stringPreferencesKey("phone")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val COMPANY_KEY = stringPreferencesKey("company")
        private val INTRODUCTION_KEY = stringPreferencesKey("introduction")
        private val AVATAR_PATH_KEY = stringPreferencesKey("avatar_path")
        private val TEMPLATE_ID_KEY = stringPreferencesKey("template_id")
    }

    fun getFullName(): Flow<String> =
        context.dataStore.data.map { preferences ->
            preferences[FULL_NAME_KEY] ?: ""
        }

    fun getPosition(): Flow<String> =
        context.dataStore.data.map { preferences ->
            preferences[POSITION_KEY] ?: ""
        }

    fun getPhone(): Flow<String> =
        context.dataStore.data.map { preferences ->
            preferences[PHONE_KEY] ?: ""
        }

    fun getEmail(): Flow<String> =
        context.dataStore.data.map { preferences ->
            preferences[EMAIL_KEY] ?: ""
        }

    fun getCompany(): Flow<String> =
        context.dataStore.data.map { preferences ->
            preferences[COMPANY_KEY] ?: ""
        }

    fun getIntroduction(): Flow<String> =
        context.dataStore.data.map { preferences ->
            preferences[INTRODUCTION_KEY] ?: ""
        }

    fun getAvatarPath(): Flow<String> =
        context.dataStore.data.map { preferences ->
            preferences[AVATAR_PATH_KEY] ?: ""
        }

    fun getTemplateId(): Flow<String> =
        context.dataStore.data.map { preferences ->
            preferences[TEMPLATE_ID_KEY] ?: "minimal_white"
        }

    fun getCardData(): Flow<BusinessCardData> =
        context.dataStore.data.map { preferences ->
            BusinessCardData(
                fullName = preferences[FULL_NAME_KEY] ?: "",
                position = preferences[POSITION_KEY] ?: "",
                phone = preferences[PHONE_KEY] ?: "",
                email = preferences[EMAIL_KEY] ?: "",
                company = preferences[COMPANY_KEY] ?: "",
                introduction = preferences[INTRODUCTION_KEY] ?: "",
                avatarPath = preferences[AVATAR_PATH_KEY] ?: "",
                templateId = preferences[TEMPLATE_ID_KEY] ?: "minimal_white"
            )
        }

    suspend fun saveCardData(cardData: BusinessCardData) {
        context.dataStore.edit { preferences ->
            preferences[FULL_NAME_KEY] = cardData.fullName
            preferences[POSITION_KEY] = cardData.position
            preferences[PHONE_KEY] = cardData.phone
            preferences[EMAIL_KEY] = cardData.email
            preferences[COMPANY_KEY] = cardData.company
            preferences[INTRODUCTION_KEY] = cardData.introduction
            preferences[AVATAR_PATH_KEY] = cardData.avatarPath
            preferences[TEMPLATE_ID_KEY] = cardData.templateId
        }
    }

    suspend fun clearAll() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}

data class BusinessCardData(
    val fullName: String = "",
    val position: String = "",
    val phone: String = "",
    val email: String = "",
    val company: String = "",
    val introduction: String = "",
    val avatarPath: String = "",
    val templateId: String = "minimal_white"
)
