package com.manuelmarvelapp.ui.main.view.comics

import androidx.fragment.app.activityViewModels
import com.manuelmarvelapp.R
import com.manuelmarvelapp.data.model.marvel.info_response.InfoResultItem
import com.manuelmarvelapp.databinding.FragmentComicsBinding
import com.manuelmarvelapp.ui.main.adapter.InfoAdapter
import com.manuelmarvelapp.ui.main.view.base.BaseFragment
import com.manuelmarvelapp.ui.main.view.comics.viewmodel.ComicsViewModel
import com.manuelmarvelapp.utils.Status
import com.manuelmarvelapp.utils.toast

class ComicsFragment : BaseFragment<FragmentComicsBinding>(R.layout.fragment_comics) {

    private lateinit var binding: FragmentComicsBinding
    private lateinit var adapter: InfoAdapter
    private val viewModel: ComicsViewModel by activityViewModels()

    override fun FragmentComicsBinding.initialize() {
        binding = this
        setRecycler()
        observableComics()
    }

    private fun setRecycler(){
        adapter = InfoAdapter()
        binding.rvComics.adapter = adapter
    }

    private fun observableComics(){
        viewModel.getComicsFromServices(idCharacter ?: 0)
        viewModel.getComics().observe(viewLifecycleOwner, {
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
        private var idCharacter : Int? = null
        private var INSTANCE: ComicsFragment? = null

        fun newInstance(idCharacter: Int): ComicsFragment{
            this.idCharacter = idCharacter

            return if (INSTANCE != null){
               INSTANCE!!
            }else{
                ComicsFragment()
            }
        }
    }
}