package com.billysoft.cookingrecipeapp.ui.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.billysoft.cookingrecipeapp.R
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

        viewModel.resultsCount.observe(viewLifecycleOwner) { count ->
            binding?.textResults?.text = resources.getQuantityText(
                R.plurals.placeholder_results_count, count
            ).toString().format(count)

        }

        viewModel.uiEvent.observe(viewLifecycleOwner) { event ->
            when (event) {
                RecipeListViewModel.UiEvent.ShowLoading -> {
                    binding?.groupShimmerViews?.visibility = View.VISIBLE
                    binding?.groupUiViews?.visibility = View.INVISIBLE
                }
                RecipeListViewModel.UiEvent.HideLoading -> {
                    binding?.groupShimmerViews?.visibility = View.GONE
                    binding?.groupUiViews?.visibility = View.VISIBLE
                }
            }
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