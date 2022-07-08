package com.feyza.applicationecommerce.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.feyza.applicationecommerce.database.model.ItemModel
import com.feyza.applicationecommerce.databinding.BagLayoutBinding
import com.squareup.picasso.Picasso

class BagAdapter :RecyclerView.Adapter<BagAdapter.BagViewHolder>() {
    private val bagList=ArrayList<ItemModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagViewHolder {
        val bagBinding = BagLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BagViewHolder(bagBinding)
    }
    override fun onBindViewHolder(holder: BagViewHolder, position: Int) {
        holder.bind((bagList[position]))
    }
    override fun getItemCount(): Int {
       return bagList.size
    }
    inner class BagViewHolder(private var bagBinding:BagLayoutBinding):RecyclerView.ViewHolder(bagBinding.root){
        fun bind(bagModel: ItemModel){
            bagBinding.apply {
                bagModel.image.let {
                    Picasso.get().load(it).into(imageBag)
                }
                itembag=bagModel
            }
        }
    }
    fun bagupdateList(list: List<ItemModel>){
        bagList.clear()
        bagList.addAll(list)
    }
}