package com.koko.foodiekotlin.ui.fragments.recipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.koko.foodiekotlin.viewmodels.MainViewModel
import com.koko.foodiekotlin.R
import com.koko.foodiekotlin.adapters.RecipeAdapters
import com.koko.foodiekotlin.util.Constants
import com.koko.foodiekotlin.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_recipes.view.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private val mAdapter by lazy { RecipeAdapters() }
    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_recipes, container, false)

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        setupRecyclerView()
        readDatabase()
        return mView
    }

    private fun setupRecyclerView() {
        mView.shimmer_recycler_view.adapter = mAdapter
        mView.shimmer_recycler_view.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun readDatabase() {
        lifecycleScope.launch {
            mainViewModel.readRecipes.observe(viewLifecycleOwner , { database ->
                if (database.isNotEmpty()){
                    Log.d("RecipesFragment", "request Database Code Called")
                    mAdapter.setData(database[0].foodRecipe)
                    hideShimmerEffect()
                }else
                    requestApiData()
            })
        }
    }

    private fun requestApiData() {
        Log.d("RecipesFragment", "request Api Code Called")
        mainViewModel.getRecipes(applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner, {response ->
            when(response){
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    loadDataFromCache()
                    Toast.makeText(
                            requireContext(),
                            response.message.toString(),
                            Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        })
    }

    private fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries["number"] = "50"
        queries["apiKey"] = Constants.APIKEY
        queries["type"] = "snack"
        queries["diet"] = "vegan"
        queries["addRecipeInformation"] = "true"
        queries["fillIngredients"] = "true"


        return queries
    }

     private fun loadDataFromCache(){
        lifecycleScope.launch {
            mainViewModel.readRecipes.observe(viewLifecycleOwner, { database ->
                if (database.isNotEmpty()) {
                    mAdapter.setData(database[0].foodRecipe)
                }

            })
        }

     }

    private fun showShimmerEffect() {
        mView.shimmer_recycler_view.showShimmer()
    }

    private fun hideShimmerEffect() {
        mView.shimmer_recycler_view.hideShimmer()
    }

}