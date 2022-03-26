package com.manuelmarvelapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manuelmarvelapp.R
import com.manuelmarvelapp.data.model.marvel.character_response.ResultsItem
import com.manuelmarvelapp.databinding.ItemMarvelBinding

class HeroesAdapater(
    val heroesList: ArrayList<ResultsItem> = arrayListOf(),
    private val listener: (click: ResultsItem) -> Unit
) :
    RecyclerView.Adapter<HeroesAdapater.HeroesViewHolder>() {

    inner class HeroesViewHolder(private val view: ItemMarvelBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(item: ResultsItem) {
            itemView.run {
                view.tvName.text = item.name ?: ""
                Glide.with(itemView.context)
                    .load(item.thumbnail?.path + "/portrait_fantastic." + item.thumbnail?.extension)
                    .placeholder(R.drawable.placeholder)
                    .into(view.ivHeroe)

            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeroesAdapater.HeroesViewHolder {
        val rootView = ItemMarvelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeroesViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: HeroesAdapater.HeroesViewHolder, position: Int) {
        holder.bind(heroesList[position])
        holder.itemView.setOnClickListener {
            listener(heroesList[position])
        }
    }

    override fun getItemCount(): Int = heroesList.size

    fun addItems(heroesList: ArrayList<ResultsItem>) {
        heroesList.forEachIndexed { index, resultsItem ->
            this.heroesList.add(resultsItem)
        }
        notifyDataSetChanged()
    }
}