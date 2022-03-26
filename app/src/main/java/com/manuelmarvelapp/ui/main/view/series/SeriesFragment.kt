package com.manuelmarvelapp.ui.main.view.series

import androidx.fragment.app.activityViewModels
import com.manuelmarvelapp.R
import com.manuelmarvelapp.data.model.marvel.info_response.InfoResultItem
import com.manuelmarvelapp.databinding.FragmentSeriesBinding
import com.manuelmarvelapp.ui.main.adapter.InfoAdapter
import com.manuelmarvelapp.ui.main.view.base.BaseFragment
import com.manuelmarvelapp.ui.main.view.series.viewmodel.SeriesViewModel
import com.manuelmarvelapp.utils.Status
import com.manuelmarvelapp.utils.toast

class SeriesFragment : BaseFragment<FragmentSeriesBinding>(R.layout.fragment_series) {

    private lateinit var binding: FragmentSeriesBinding
    private lateinit var adapter: InfoAdapter
    private val viewModel: SeriesViewModel by activityViewModels()

    override fun FragmentSeriesBinding.initialize() {
        binding = this
        setRecycler()
        observableSeries()
    }

    private fun setRecycler(){
        adapter = InfoAdapter()
        binding.rvSeries.adapter = adapter
    }

    private fun observableSeries(){
        viewModel.getComicsFromServices(idCharacter ?: 0)
        viewModel.getSeries().observe(viewLifecycleOwner, {
            binding.resource = it // Set DataBinding

            when (it.status) {
                Status.SUCCESS -> {
                    adapter.infoList.clear()
                    adapter.addItems((it.data ?: arrayListOf()) as ArrayList<InfoResultItem>)
                }

                Status.ERROR -> {
                    toast(it.message ?: "")
                }

                Status.LOADING ->{
                    //Nothing to do
                }
            }
        })
    }

    companion object{
        private var idCharacter: Int? = null
        private var INSTANCE: SeriesFragment? = null

        fun newInstance(idCharacter: Int): SeriesFragment {
           this.idCharacter = idCharacter

            return if (INSTANCE != null){
                INSTANCE!!
            }else{
                SeriesFragment()
            }
        }
    }
}