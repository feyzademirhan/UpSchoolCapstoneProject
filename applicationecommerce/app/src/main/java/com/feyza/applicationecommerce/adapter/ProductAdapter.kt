package com.feyza.applicationecommerce.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.feyza.applicationecommerce.HomePageFragmentDirections
import com.feyza.applicationecommerce.database.model.ItemModel
import com.feyza.applicationecommerce.databinding.ProductLayoutBinding
import com.squareup.picasso.Picasso

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private val productlist=ArrayList<ItemModel>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductAdapter.ProductViewHolder {
        val productsBinding=ProductLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(productsBinding)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) {
        holder.bind(productlist[position])
    }

    override fun getItemCount(): Int =productlist.size

    inner class ProductViewHolder(private var productsBinding:ProductLayoutBinding):RecyclerView.ViewHolder(productsBinding.root){

        fun bind(productmodel:ItemModel){
            productsBinding.apply {
                productmodel.image.let {
                    Picasso.get().load(it).into(imageView)
                }
                itemProducts=productmodel

                imageView.setOnClickListener {
                    val action= HomePageFragmentDirections.actionHomePageFragmentToItemCardFragment(productmodel)
                        it.findNavController().navigate(action)
                }
            }
        }

    }

    fun updateList(list: List<ItemModel>){
        productlist.clear()
        productlist.addAll(list)
    }


}