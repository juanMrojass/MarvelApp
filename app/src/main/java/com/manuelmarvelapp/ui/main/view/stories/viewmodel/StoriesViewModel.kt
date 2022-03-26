package com.manuelmarvelapp.ui.main.view.stories.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelmarvelapp.data.model.marvel.info_response.InfoResponse
import com.manuelmarvelapp.data.model.marvel.info_response.InfoResultItem
import com.manuelmarvelapp.data.repository.MarvelRepository
import com.manuelmarvelapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoriesViewModel @Inject constructor(
    private val repository: MarvelRepository
): ViewModel() {

    private val stories = MutableLiveData<Resource<List<InfoResultItem?>>>()

    fun getComicsFromServices(id: Int){
        viewModelScope.launch {
            stories.postValue(Resource.loading(null))
            val result =  repository.getStories(id)

            if (result.data is InfoResponse){
                val data = result.data

                if (data.code in 200..299){
                    stories.postValue(Resource.success(data = data.dataInfo?.results))
                }else{
                    stories.postValue(Resource.error(result.message ?: "", null))
                }
            }else{
                val data = result.data as Exception
                stories.postValue(Resource.error(data.message, null))
            }
        }
    }

    fun getStories() = stories
}