package com.example.sportresults.ui.results

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sportresults.databinding.FragmentResultsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResultsFragment : Fragment() {

    private lateinit var resultsBinding: FragmentResultsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        resultsBinding = FragmentResultsBinding.inflate(inflater, container, false)

        resultsBinding.getResultsBtn.setOnClickListener {
            this.findNavController().navigate(ResultsFragmentDirections.actionNavigateToResultsList())
        }

        return resultsBinding.root
    }
}