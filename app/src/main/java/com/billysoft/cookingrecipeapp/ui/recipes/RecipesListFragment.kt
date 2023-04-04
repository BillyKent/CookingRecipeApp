package com.billysoft.cookingrecipeapp.ui.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.billysoft.cookingrecipeapp.databinding.FragmentRecipesListBinding
import com.billysoft.domain.model.Recipe
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecipesListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()
    private var binding: FragmentRecipesListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipesListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
            binding?.recyclerRecipeList?.adapter = RecipeListItemAdapter(recipes, ::onRecipeClicked)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun onRecipeClicked(recipe: Recipe) {
        findNavController().navigate(
            RecipesListFragmentDirections.navigateToRecipeDetailFragment(
                recipeId = recipe.id
            )
        )
    }

}