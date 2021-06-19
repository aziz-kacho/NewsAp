package com.example.newsapp.ui.save_historyFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.Models.HistoryNewsEntity.ArticlesHistoryNews
import com.example.newsapp.data.Models.HistoryNewsEntity.NewsResponseHistory
import com.example.newsapp.utils.OnClickHistory
import tj.livo.newsapp.models.NewsResponseSave

class HistoryFragment : Fragment(), OnClickHistory, FragmentManager.OnBackStackChangedListener {

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



    }


    /// Кнопка назад ///
    override fun onBackStackChanged() {
        
    }


}