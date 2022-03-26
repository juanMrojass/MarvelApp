package com.manuelmarvelapp.ui.main.view.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelmarvelapp.data.model.marvel.character_response.MarvelResponse
import com.manuelmarvelapp.data.model.marvel.character_response.ResultsItem
import com.manuelmarvelapp.data.repository.MarvelRepository
import com.manuelmarvelapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MarvelRepository
): ViewModel(){

    private val heroes = MutableLiveData<Resource<List<ResultsItem?>>>()

    fun getHeroesFromService(){
        viewModelScope.launch {
            heroes.postValue(Resource.loading(null)) //Loading
            val result = repository.getCharacters()

            if (result.data is MarvelResponse){
                val data = result.data
                if (data.code in 200..299){
                    heroes.postValue(Resource.success(data = data.data?.results))
                }else{
                    heroes.postValue(Resource.error(result.message?: "", null))
                }
            }else{
                val data = result.data as Exception
                heroes.postValue(Resource.error(data.message, null))
            }
        }
    }

    fun getHeroes() = heroes
 }