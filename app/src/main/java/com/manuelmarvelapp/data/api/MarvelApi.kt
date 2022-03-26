package com.manuelmarvelapp.data.api

import com.manuelmarvelapp.data.model.marvel.character_response.MarvelResponse
import com.manuelmarvelapp.data.model.marvel.info_response.InfoResponse
import com.manuelmarvelapp.utils.Constants.Companion.API_KEY
import com.manuelmarvelapp.utils.Constants.Companion.HASH
import com.manuelmarvelapp.utils.Constants.Companion.TS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface MarvelApi {

    @Headers("Content-Type: application/json")
    @GET("v1/public/characters?ts=$TS&apikey=$API_KEY&hash=$HASH")
    suspend fun getCharacters(): Response<MarvelResponse>

    @Headers("Content-Type: application/json")
    @GET("v1/public/characters/{id}/comics?ts=$TS&apikey=$API_KEY&hash=$HASH")
    suspend fun getComics(@Path("id") id: Int): Response<InfoResponse>

    @Headers("Content-Type: application/json")
    @GET("v1/public/characters/{id}/series?ts=$TS&apikey=$API_KEY&hash=$HASH")
    suspend fun getSeries(@Path("id") id: Int): Response<InfoResponse>

    @Headers("Content-Type: application/json")
    @GET("v1/public/characters/{id}/stories?ts=$TS&apikey=$API_KEY&hash=$HASH")
    suspend fun getStories(@Path("id") id: Int): Response<InfoResponse>
}