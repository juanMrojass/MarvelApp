package com.manuelmarvelapp.ui.main.view.series.viewmodel

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
class SeriesViewModel @Inject constructor(
    private val repository: MarvelRepository
) : ViewModel(){
    private val series = MutableLiveData<Resource<List<InfoResultItem?>>>()

    fun getComicsFromServices(id: Int){
        viewModelScope.launch {
            series.postValue(Resource.loading(null))
            val result =  repository.getSeries(id)

            if (result.data is InfoResponse){
                val data = result.data

                if (data.code in 200..299){
                    series.postValue(Resource.success(data = data.dataInfo?.results))
                }else{
                    series.postValue(Resource.error(result.message ?: "", null))
                }
            }else{
                val data = result.data as Exception
                series.postValue(Resource.error(data.message, null))
            }
        }
    }

    fun getSeries() = series
}