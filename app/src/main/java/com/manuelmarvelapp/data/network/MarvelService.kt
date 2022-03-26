package com.manuelmarvelapp.data.network

import com.manuelmarvelapp.data.api.MarvelApi
import com.manuelmarvelapp.utils.DataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MarvelService @Inject constructor(private val api: MarvelApi) {

    suspend fun getCharacters(): DataResponse =
        withContext(Dispatchers.IO) {
            try {
                val response = api.getCharacters()
                DataResponse.response(response.body(), response.message())
            } catch (e: Exception) {
                e.printStackTrace()
                DataResponse.response(e, null)
            }
        }

    suspend fun getComics(id: Int): DataResponse =
        withContext(Dispatchers.IO){
            try {
                val response = api.getComics(id)
                DataResponse.response(response.body(), response.message())
            }catch (e: Exception){
                e.printStackTrace()
                DataResponse.response(e, null)
            }
        }


    suspend fun getSeries(id: Int): DataResponse =
        withContext(Dispatchers.IO){
            try {
                val response = api.getSeries(id)
                DataResponse.response(response.body(), response.message())
            }catch (e: Exception){
                e.printStackTrace()
                DataResponse.response(e, null)
            }
        }


    suspend fun getStories(id: Int): DataResponse =
        withContext(Dispatchers.IO){
            try {
                val response = api.getStories(id)
                DataResponse.response(response.body(), response.message())
            }catch (e: Exception){
                e.printStackTrace()
                DataResponse.response(e, null)
            }
        }

}