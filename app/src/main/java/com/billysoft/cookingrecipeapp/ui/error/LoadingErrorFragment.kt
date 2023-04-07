package com.billysoft.cookingrecipeapp.ui.error

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.billysoft.cookingrecipeapp.R
import com.billysoft.cookingrecipeapp.databinding.FragmentLoadingErrorBinding
import com.billysoft.domain.model.exceptions.ExceptionCause

class LoadingErrorFragment : Fragment() {

    val screenArgs: LoadingErrorFragmentArgs by navArgs()
    private var binding: FragmentLoadingErrorBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoadingErrorBinding.inflate(inflater, container, false)

        binding?.button?.setOnClickListener {
            findNavController().navigate(LoadingErrorFragmentDirections.navigateToRecipesListFragment())
        }
        setUpError(screenArgs.errorCause)

        return binding?.root
    }

    private fun setUpError(errorCause: ExceptionCause) {
        binding?.apply {
            when (errorCause) {
                ExceptionCause.API_ERROR -> {
                    imageError.setImageResource(R.drawable.ic_server_error)
                    textErrorTitle.text = getString(R.string.oops)
                    textErrorMessage.text = getString(R.string.api_issue_message)
                }
                ExceptionCause.NETWORK_ERROR -> {
                    imageError.setImageResource(R.drawable.ic_network)
                    textErrorTitle.text = getString(R.string.no_connection)
                    textErrorMessage.text = getString(R.string.no_connection_message)
                }
                ExceptionCause.UNKNOWN_ERROR -> {
                    imageError.setImageResource(R.drawable.ic_something_wrong)
                    textErrorTitle.text = getString(R.string.whoops)
                    textErrorMessage.text = getString(R.string.unknown_error_message)

                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}