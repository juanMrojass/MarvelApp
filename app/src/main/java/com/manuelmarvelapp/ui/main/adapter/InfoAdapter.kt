package com.manuelmarvelapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manuelmarvelapp.R
import com.manuelmarvelapp.data.model.marvel.info_response.InfoResultItem
import com.manuelmarvelapp.databinding.ItemMarvelBinding

class InfoAdapter(val infoList: ArrayList<InfoResultItem> = arrayListOf()): RecyclerView.Adapter<InfoAdapter.InfoViewHolder>() {

    class InfoViewHolder(private val view: ItemMarvelBinding): RecyclerView.ViewHolder(view.root) {
        fun bind(item: InfoResultItem){
            itemView.run {
                view.tvName.text = item.title ?: ""
                Glide.with(itemView.context)
                    .load(item.thumbnail?.path + "/portrait_fantastic." + item.thumbnail?.extension)
                    .placeholder(R.drawable.placeholder)
                    .into(view.ivHeroe)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoAdapter.InfoViewHolder {
        val rootView = ItemMarvelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InfoViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: InfoAdapter.InfoViewHolder, position: Int) {
       holder.bind(infoList[position])
    }

    override fun getItemCount(): Int = infoList.size

    fun addItems(infoList: ArrayList<InfoResultItem>){
        infoList.forEachIndexed{index, info ->
            this.infoList.add(info)
        }

        notifyDataSetChanged()
    }
}