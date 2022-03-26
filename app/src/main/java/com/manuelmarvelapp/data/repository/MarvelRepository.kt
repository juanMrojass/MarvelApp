package com.manuelmarvelapp.data.repository

import com.manuelmarvelapp.data.network.MarvelService
import com.manuelmarvelapp.utils.DataResponse
import javax.inject.Inject

class MarvelRepository @Inject constructor(
    private val marvelService: MarvelService
) {
    suspend fun getCharacters(): DataResponse =
        marvelService.getCharacters()

    suspend fun getComics(id: Int): DataResponse =
        marvelService.getComics(id)

    suspend fun getSeries(id: Int): DataResponse =
        marvelService.getSeries(id)

    suspend fun getStories(id: Int): DataResponse =
        marvelService.getStories(id)
}