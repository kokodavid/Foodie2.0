package com.koko.foodiekotlin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.koko.foodiekotlin.databinding.RecipeRowLayoutBinding
import com.koko.foodiekotlin.models.FoodRecipe
import com.koko.foodiekotlin.models.Result
import com.koko.foodiekotlin.util.RecipesDiffUtil

class RecipeAdapters : RecyclerView.Adapter<RecipeAdapters.MyViewHolder>() {

    private var recipes = emptyList<Result>()

    class MyViewHolder(private val binding: RecipeRowLayoutBinding) :
            RecyclerView.ViewHolder(binding.root) {

                fun bind(result: Result){
                    binding.result = result
                    binding.executePendingBindings()
                }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecipeRowLayoutBinding.inflate(layoutInflater, parent,false)
                return  MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return  MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentRecipe = recipes[position]
        holder.bind(currentRecipe)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun setData(newData: FoodRecipe){
        val recipeDiffUtil = RecipesDiffUtil(recipes, newData.results)
        val diffUtilResult = DiffUtil.calculateDiff(recipeDiffUtil)
        recipes = newData.results
        diffUtilResult.dispatchUpdatesTo(this)

    }

}