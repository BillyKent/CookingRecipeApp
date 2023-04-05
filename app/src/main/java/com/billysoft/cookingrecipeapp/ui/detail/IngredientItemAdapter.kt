package com.billysoft.cookingrecipeapp.ui.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.billysoft.cookingrecipeapp.databinding.ItemIngredientBinding
import com.billysoft.cookingrecipeapp.ui.detail.IngredientItemAdapter.IngredientItemViewHolder
import com.billysoft.cookingrecipeapp.util.DrawableMapper
import com.billysoft.cookingrecipeapp.util.layoutInflater

class IngredientItemAdapter(
    private val drawableMapper: DrawableMapper,
    private val ingredientsCodes: List<String>,
) : Adapter<IngredientItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = IngredientItemViewHolder(
        ItemIngredientBinding.inflate(parent.layoutInflater, parent, false)
    )

    override fun getItemCount() = ingredientsCodes.size

    override fun onBindViewHolder(holder: IngredientItemViewHolder, position: Int) =
        holder.bind(ingredientsCodes[position])

    inner class IngredientItemViewHolder(
        private val binding: ItemIngredientBinding,
    ) : ViewHolder(binding.root) {

        fun bind(ingredientCode: String) {
            binding.iconIngredient.setImageResource(
                drawableMapper.getIngredientDrawable(
                    ingredientCode
                )
            )
        }

    }
}