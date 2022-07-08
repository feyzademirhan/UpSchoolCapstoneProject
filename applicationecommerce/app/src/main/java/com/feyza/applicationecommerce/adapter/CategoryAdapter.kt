package com.feyza.applicationecommerce.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.feyza.applicationecommerce.databinding.ItemCategoryBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private val categoryList=ArrayList<String>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.CategoryViewHolder {
        val categoryBinding=ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryViewHolder(categoryBinding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
    inner class CategoryViewHolder(private var categoryBinding:ItemCategoryBinding):RecyclerView.ViewHolder(categoryBinding.root){
            fun bind(productModel: String){
                categoryBinding.categoryText.text=productModel
        }
    }

    fun updateList(list: List<String>){
        categoryList.clear()
        categoryList.addAll(list)
    }
}