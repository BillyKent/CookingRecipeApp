package com.billysoft.cookingrecipeapp.ui.recipes

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.billysoft.cookingrecipeapp.databinding.ItemRecipeListBinding
import com.billysoft.cookingrecipeapp.ui.recipes.RecipeListItemAdapter.RecipeListItemViewHolder
import com.billysoft.cookingrecipeapp.util.Formatters
import com.billysoft.cookingrecipeapp.util.layoutInflater
import com.billysoft.cookingrecipeapp.util.loadImageFromUrl
import com.billysoft.domain.model.Recipe
import com.squareup.picasso.Picasso

class RecipeListItemAdapter(
    private val items: List<Recipe>,
    private val onRecipeClicked: (Recipe) -> Unit,
) : Adapter<RecipeListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecipeListItemViewHolder(
        ItemRecipeListBinding.inflate(parent.layoutInflater, parent, false)
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecipeListItemViewHolder, position: Int) =
        holder.bind(items[position])

    inner class RecipeListItemViewHolder(private val binding: ItemRecipeListBinding) :
        ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.apply {
                cardRecipeItem.setOnClickListener {
                    onRecipeClicked(recipe)
                }
                textRecipeTitle.text = recipe.title
                textCookingTime.text = Formatters.formatTime(recipe.cookingTime)
                textRatingStars.text = Formatters.formatRatingStars(recipe.rating)
                imageRecipePhoto.loadImageFromUrl(recipe.photoUrl)
            }
        }

    }

}