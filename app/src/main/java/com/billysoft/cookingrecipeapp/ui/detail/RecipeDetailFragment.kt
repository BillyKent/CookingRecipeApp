package com.billysoft.cookingrecipeapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.billysoft.cookingrecipeapp.databinding.FragmentRecipeDetailBinding
import com.billysoft.cookingrecipeapp.util.DrawableMapper
import com.billysoft.cookingrecipeapp.util.Formatters
import com.billysoft.cookingrecipeapp.util.loadImageFromUrl
import com.billysoft.domain.model.Recipe
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {

    private val viewModel: RecipeDetailViewModel by viewModels()
    private var binding: FragmentRecipeDetailBinding? = null
    private lateinit var drawableMapper: DrawableMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        drawableMapper = DrawableMapper()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.imageArrowBack?.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.recipeDetail.observe(viewLifecycleOwner) { recipe ->
            setUpView(recipe)
        }

    }

    private fun setUpView(recipe: Recipe) {
        binding?.apply {
            textRecipeTitle.text = recipe.title
            textRecipeDescription.text = recipe.description
            textRatingStars.text = Formatters.formatRatingStars(recipe.rating)
            textCookingTime.text = Formatters.formatTime(recipe.cookingTime)
            imageRecipePhoto.loadImageFromUrl(recipe.photoUrl)

            recyclerIngredients.adapter =
                IngredientItemAdapter(drawableMapper, recipe.ingredients)

            binding?.fabMapOrigin?.setOnClickListener {
                findNavController().navigate(
                    RecipeDetailFragmentDirections.navigateToRecipeOriginFragment(recipe.id)
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}