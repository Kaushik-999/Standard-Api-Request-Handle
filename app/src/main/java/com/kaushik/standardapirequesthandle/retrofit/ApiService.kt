package com.kaushik.standardapirequesthandle.retrofit

import android.net.wifi.hotspot2.pps.Credential
import com.kaushik.standardapirequesthandle.Constants
import com.kaushik.standardapirequesthandle.retrofit.models.Posts
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(Constants.PATH)
    suspend fun getPost(): Response<Posts>
}