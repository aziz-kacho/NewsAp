package com.example.newsapp.ui.save_historyFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.Models.HistoryNewsEntity.ArticlesHistoryNews
import com.example.newsapp.data.Models.HistoryNewsEntity.NewsResponseHistory
import com.example.newsapp.utils.OnClickHistory

class HistoryFragment : Fragment(), OnClickHistory {

    private lateinit var viewModel: HistoryViewModel
    private var getNewsResponse = MutableLiveData<NewsResponseHistory>()
    private lateinit var recyclerViewHistoryNews: RecyclerView
    private lateinit var adapterHistoryNewsNews: HistoryNewsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        recyclerViewHistoryNews = view.findViewById(R.id.historyRecyclerView)
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerViewHistoryNews.layoutManager = layoutManager
        adapterHistoryNewsNews = HistoryNewsAdapter(this, this)
        recyclerViewHistoryNews.adapter = adapterHistoryNewsNews


        viewModel.readAllHistoryNews.observe(viewLifecycleOwner) {
            adapterHistoryNewsNews.setData(it)
        }

    }

    override fun onclickListener(articlesHistoryNews: ArticlesHistoryNews) {

        val bundle: Bundle = Bundle()
        bundle.putParcelable("article", articlesHistoryNews)
        findNavController().navigate(R.id.recentHistoryFragment, bundle)

    }
}