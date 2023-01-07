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
import com.example.sportresults.utils.convertLongToTime
import com.example.sportresults.utils.getDateOnly
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

        var resultList = mutableMapOf<Long, LinkedHashSet<Pair<Long, String>>>()

        resultViewModel.foneResultsLatest.observe(viewLifecycleOwner, Observer {
            if(it != null){
                val foneLatest = it.winner + " wins " + it.tournament + " by " +
                        it.seconds + " seconds"
                resultsSetHelper(it.publicationDate, resultList, foneLatest)
            }
        })

        resultViewModel.nbaResultsLatest.observe(viewLifecycleOwner, Observer {
            if(it != null){
                val nbaLatest = it.mvp + " leads " + it.winner + " to game "+
                        it.gameNumber + " win in the " + it.tournament
                resultsSetHelper(it.publicationDate, resultList, nbaLatest)
            }
        })

        resultViewModel.tennisResultsLatest.observe(viewLifecycleOwner, Observer {
            if(it != null){
                val tennisLatest = it.tournament + ": " + it.winner + " wins against " + it.looser +
                        " in " + it.numberOfSets + " sets"
                resultsSetHelper(it.publicationDate, resultList, tennisLatest)
            }
        })

        resultViewModel.latestResults.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.toList())
        })

        return resultListBinding.root
    }

    private fun resultsSetHelper(
        publicationDate: Long,
        resultList: MutableMap<Long, LinkedHashSet<Pair<Long, String>>>,
        foneLatest: String
    ) {
        val dateOnly = getDateOnly(publicationDate)
        if (resultList.contains(dateOnly)) {
            resultList[dateOnly]!!.add(Pair(publicationDate, foneLatest))
        } else {
            resultList[dateOnly] = linkedSetOf(Pair(publicationDate, foneLatest))
        }

        // includes only the results from the most recent date
        val latest = resultList.keys.max()
        resultViewModel.mostRecentDate.value = convertLongToTime(latest)

        // sort results by date
        val resultsSortedByDate = sortByDate(resultList[latest]!!)
        resultViewModel.latestResults.value = resultsSortedByDate
    }

    private fun sortByDate(set: LinkedHashSet<Pair<Long, String>>): LinkedHashSet<String>{
        val sorted = set.sortedByDescending { it.first }
        val sortedResult = linkedSetOf<String>()
        for(r in sorted){
            sortedResult.add(r.second)
        }
        return sortedResult
    }
}