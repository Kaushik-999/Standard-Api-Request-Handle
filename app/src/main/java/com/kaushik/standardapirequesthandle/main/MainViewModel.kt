package com.kaushik.standardapirequesthandle.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaushik.standardapirequesthandle.utils.Resource
import com.kaushik.standardapirequesthandle.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Empty)
    val uiState = _uiState

    fun getData(){
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UiState.Loading

            repository.getPosts()
                .catch { e->
                    Log.d("error MainViewModel", e.stackTraceToString())
                }
                .collect {
                    when(it) {
                        is Resource.Error -> {
                            _uiState.value = UiState.Error(it.errorMessage)
                        }
                        is Resource.Success -> {
                            _uiState.value = UiState.Success(it.data!!)
                        }
                        else -> Unit
                    }
                }
        }
    }



}