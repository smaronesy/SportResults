package com.example.sportresults.ui.results

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportresults.databinding.FragmentResultsListBinding
import com.example.sportresults.repository.ResultsApiStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResultsListFragment : Fragment() {

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var resultListBinding: FragmentResultsListBinding
    private val resultViewModel: ResultsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        resultListBinding = FragmentResultsListBinding.inflate(inflater, container, false)

        resultListBinding.listViewModel = resultViewModel
        resultListBinding.lifecycleOwner = this

        val adapter = RecyclerViewAdapter(ResultsListener {  })

        resultListBinding.resultsRecycler.adapter = adapter
        layoutManager = LinearLayoutManager(context);
        resultListBinding.resultsRecycler.layoutManager = layoutManager;

        val dividerItemDecoration = DividerItemDecoration(
            resultListBinding.resultsRecycler.getContext(),
            layoutManager.getOrientation()
        )

        resultListBinding.resultsRecycler.addItemDecoration(dividerItemDecoration)

        var resultList = mutableSetOf<String>()
        resultViewModel.foneResultsLatest.observe(viewLifecycleOwner, Observer {
            if(it != null){
                val foneLatest = it.winner + " wins " + it.tournament + " by " +
                        it.seconds + " seconds"
                resultList.add(foneLatest)
                resultViewModel.latestResults.value = resultList
            }
        })

        resultViewModel.nbaResultsLatest.observe(viewLifecycleOwner, Observer {
            if(it != null){
                val nbaLatest = it.mvp + " leads " + it.winner + " to game "+
                        it.gameNumber + " win in the " + it.tournament
                resultList.add(nbaLatest)
                resultViewModel.latestResults.value = resultList
            }
        })

        resultViewModel.tennisResultsLatest.observe(viewLifecycleOwner, Observer {
            if(it != null){
                val tennisLatest = it.tournament + ": " + it.winner + " wins against " + it.looser +
                        " in " + it.numberOfSets + " sets"
                resultList.add(tennisLatest)
                resultViewModel.latestResults.value = resultList
            }
        })

        resultViewModel.latestResults.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.toList())
        })

        return resultListBinding.root
    }
}