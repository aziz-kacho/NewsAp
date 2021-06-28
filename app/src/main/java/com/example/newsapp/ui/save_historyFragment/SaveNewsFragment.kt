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
import com.example.newsapp.utils.OnClickFavorites
import tj.livo.newsapp.models.ArticlesSaveNews
import tj.livo.newsapp.models.NewsResponseSave

class SaveNewsFragment : Fragment(), OnClickFavorites {
    private lateinit var viewModel: SaveViewModel
    private lateinit var recyclerViewSavedNews: RecyclerView
    private lateinit var adapterSaveNewsNews: SaveNewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_saved_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(SaveViewModel::class.java)
        recyclerViewSavedNews = view.findViewById(R.id.saveRecyclerView)
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerViewSavedNews.layoutManager = layoutManager
        adapterSaveNewsNews = SaveNewsAdapter(this, this)
        recyclerViewSavedNews.adapter = adapterSaveNewsNews


        viewModel.readAllData.observe(viewLifecycleOwner) {
            adapterSaveNewsNews.setData(it)
        }

    }

    override fun onclickListener(articlesSaveNews: ArticlesSaveNews) {
        val bundle: Bundle = Bundle()
        bundle.putParcelable("article", articlesSaveNews)
        findNavController().navigate(R.id.chosenNewsFragment, bundle)
    }


}
