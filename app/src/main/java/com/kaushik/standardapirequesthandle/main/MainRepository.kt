package com.kaushik.standardapirequesthandle.main

import com.kaushik.standardapirequesthandle.retrofit.ApiService
import com.kaushik.standardapirequesthandle.utils.result
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
){
    fun getPosts() = result {
        apiService.getPost()
    }

}