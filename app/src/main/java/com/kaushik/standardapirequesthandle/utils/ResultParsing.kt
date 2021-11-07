package com.kaushik.standardapirequesthandle.utils

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.lang.Error
import kotlin.Exception

fun <T>result(call:suspend () -> Response<T>): Flow<Resource<T>> = flow {

    emit(Resource.Loading)

    try {
        val c = call()

        c.let { response->
            if(response.isSuccessful && response.body() != null) {
                emit(Resource.Success(response.body()))
            } else {
                if (response.body() == null){
                    Log.d("error ResultParsing", "Null Body")
                    emit(Resource.Error("Null Body"))
                    return@flow
                }
                response.errorBody()?.let { error->
                    error.close()
                    Log.d("error ResultParsing", error.toString())
                    emit(Resource.Error(error.toString()))
                }
            }
        }

    } catch (e: Exception) {
        Log.d("error ResultParsing", e.localizedMessage)
        emit(Resource.Error(e.localizedMessage))
    }

}.flowOn(Dispatchers.IO)