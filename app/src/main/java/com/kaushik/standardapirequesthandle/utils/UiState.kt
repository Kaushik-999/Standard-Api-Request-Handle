package com.kaushik.standardapirequesthandle.utils

import com.kaushik.standardapirequesthandle.retrofit.models.Posts

sealed class UiState {
    object Empty: UiState()
    object Loading: UiState()
    data class Success(val data: Posts): UiState()
    data class Error(val errorMessage: String?): UiState()
}