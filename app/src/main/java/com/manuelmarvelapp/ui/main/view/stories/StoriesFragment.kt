package com.manuelmarvelapp.ui.main.view.stories

import androidx.fragment.app.activityViewModels
import com.manuelmarvelapp.R
import com.manuelmarvelapp.data.model.marvel.info_response.InfoResultItem
import com.manuelmarvelapp.databinding.FragmentStoriesBinding
import com.manuelmarvelapp.ui.main.adapter.InfoAdapter
import com.manuelmarvelapp.ui.main.view.base.BaseFragment
import com.manuelmarvelapp.ui.main.view.stories.viewmodel.StoriesViewModel
import com.manuelmarvelapp.utils.Status
import com.manuelmarvelapp.utils.toast

class StoriesFragment: BaseFragment<FragmentStoriesBinding>(R.layout.fragment_stories) {

    private lateinit var binding: FragmentStoriesBinding
    private lateinit var adapter: InfoAdapter
    private val viewModel: StoriesViewModel by activityViewModels()

    override fun FragmentStoriesBinding.initialize() {
        binding = this
        setRecycler()
        observableSeries()
    }

    private fun setRecycler(){
        adapter = InfoAdapter()
        binding.rvStories.adapter = adapter
    }

    private fun observableSeries(){
        viewModel.getComicsFromServices(idCharacter ?: 0)
        viewModel.getStories().observe(viewLifecycleOwner, {
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
        private var INSTANCE: StoriesFragment? = null

        fun newInstance(idCharacter: Int): StoriesFragment{
            this.idCharacter = idCharacter

            return if (INSTANCE != null){
                INSTANCE!!
            }else{
                StoriesFragment()
            }
        }
    }
}