package com.billysoft.cookingrecipeapp.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.billysoft.cookingrecipeapp.R
import com.billysoft.cookingrecipeapp.databinding.FragmentRecipeOriginBinding
import com.billysoft.cookingrecipeapp.ui.detail.RecipeDetailViewModel
import com.billysoft.cookingrecipeapp.util.Formatters
import com.billysoft.cookingrecipeapp.util.animate
import com.billysoft.cookingrecipeapp.util.loadImageFromUrl
import com.billysoft.domain.model.RecipeOrigin
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.ktx.addMarker
import com.google.maps.android.ktx.awaitMap
import com.google.maps.android.ktx.awaitMapLoad
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RecipeOriginFragment : Fragment() {

    private val viewModel: RecipeDetailViewModel by viewModels()
    private lateinit var googleMap: GoogleMap
    private var binding: FragmentRecipeOriginBinding? = null
    private var job: Job? = null
    private var cameraJob: Job? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecipeOriginBinding.inflate(inflater, container, false)
        binding?.fabArrowBack?.setOnClickListener {
            if (cameraJob?.isActive == true) {
                cameraJob?.cancel()
            }
            if (job?.isActive == true) {
                job?.cancel()
            }
            findNavController().navigateUp()

        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.recipeDetail.observe(viewLifecycleOwner) { recipe ->
            setUpContent(recipe.origin)
        }

        job = lifecycleScope.launchWhenCreated {
            val mapFragment: SupportMapFragment? =
                childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
            val googleMap: GoogleMap? = mapFragment?.awaitMap()

            if (googleMap != null) {
                this@RecipeOriginFragment.googleMap = googleMap
                setUpMap(view)
                moveToInitialPosition()
                googleMap.awaitMapLoad()
                viewModel.recipeDetail.observe(viewLifecycleOwner) { recipe ->
                    val origin = recipe.origin
                    animateCameraToCoordinates(origin.latitude, origin.longitude)
                }

            } else {
                Toast.makeText(requireContext(), "Something goes wrong", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
        }
    }

    private fun setUpMap(view: View) {
        try {
            googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(requireContext(), R.raw.map_style)
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        googleMap.uiSettings.apply {
            setAllGesturesEnabled(false)
            isMyLocationButtonEnabled = false
            isMapToolbarEnabled = false
        }
        binding?.bottomDrawer?.y?.let {
            googleMap.setPadding(0, 0, 0, view.height - it.toInt())
        }
    }

    private fun enableMapGestures() {
        googleMap.uiSettings.apply {
            isScrollGesturesEnabled = true
            isZoomGesturesEnabled = true
        }
    }

    private fun moveToInitialPosition() {
        googleMap.moveCamera(
            CameraUpdateFactory.newCameraPosition(
                CameraPosition.fromLatLngZoom(LatLng(-12.615195, -76.467544), 8f)
            )
        )
    }

    private fun setUpContent(origin: RecipeOrigin) {
        binding?.containerContent?.apply {
            textOriginTitle.text = origin.name
            textOriginCountry.text = origin.country
            textOriginDescription.text = origin.description
            imageLocation.loadImageFromUrl(origin.referencePhotoUrl)
            textCoordenates.text = Formatters.formatCoordinates(origin.latitude, origin.longitude)
            buttonKnowMore.setOnClickListener {
                Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun animateCameraToCoordinates(latitude: Double, longitude: Double) {
        val latLng = LatLng(latitude, longitude)
        cameraJob = lifecycleScope.launch {
            googleMap.addMarker {
                position(latLng)
            }
            googleMap.animate(
                CameraUpdateFactory.newCameraPosition(
                    CameraPosition.fromLatLngZoom(latLng, 8f)
                ),
                700
            )
            googleMap.awaitMapLoad()
            googleMap.animate(
                CameraUpdateFactory.zoomTo(14f),
                1200
            )
            enableMapGestures()
        }

    }


}