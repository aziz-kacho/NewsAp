package com.example.newsapp.data.Models.FragmentAllNews

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.R
import com.example.newsapp.data.Models.HistoryNewsEntity.ArticlesHistoryNews
import com.example.newsapp.ui.save_historyFragment.HistoryViewModel
import com.example.newsapp.ui.save_historyFragment.SaveViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import tj.livo.newsapp.models.Articles
import tj.livo.newsapp.models.ArticlesSaveNews


class AllNewsFragment : Fragment() {

    private lateinit var articles: Articles

    private lateinit var webView: WebView

    private lateinit var saveNewsButton: FloatingActionButton

    private lateinit var viewModel: SaveViewModel


    private lateinit var articlesSaveNews: ArticlesSaveNews

    private lateinit var viewModelHistoryNews: HistoryViewModel
    private lateinit var articlesHistoryNews: ArticlesHistoryNews


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_all_news, container, false)
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (arguments?.getParcelable<Articles>("article") != null) {
            articles = arguments!!.getParcelable<Articles>("article")!!
        }
        webView = view.findViewById(R.id.webView)
        articles.url?.let { webView.loadUrl(it) }


        viewModel = ViewModelProvider(this).get(SaveViewModel::class.java)
        saveNewsButton = view.findViewById(R.id.saveNews_button)
        viewModel.readAllData.observe(viewLifecycleOwner) { it ->
            it.forEach {
                articles.isFavorite = articles.id == it.id
            }
            articlesSaveNews = ArticlesSaveNews(
                articles.id,
                articles.type,
                articles.isFavorite,
                articles.author,
                articles.title,
                articles.description,
                articles.url,
                articles.urlToImage,
                articles.publishedAt,
                articles.content
            )
        }


        ////////////////////////////////////////////////


        viewModelHistoryNews = ViewModelProvider(this).get(HistoryViewModel::class.java)


        if (arguments!!.getParcelable<Articles>("article") != null) {
            articles = arguments!!.getParcelable<Articles>("article")!!
        }


        viewModelHistoryNews.readAllHistoryNews.observe(viewLifecycleOwner) {
            it.forEach {
                articles.isFavorite = articles.title == it.title
            }

            ArticlesHistoryNews(
                articles.id!!,
                articles.type,
                articles.isFavorite,
                articles.author,
                articles.title,
                articles.description,
                articles.url,
                articles.urlToImage,
                articles.publishedAt,
                articles.content
            ).also { articlesHistoryNews = it }

            if (!articlesHistoryNews.isFavorite) {
                articlesHistoryNews.isFavorite = true
                viewModelHistoryNews.addNewsInArticles(articlesHistoryNews)

            }
        }

////////////////////////////////


        saveNewsButton.setOnClickListener {
            if (!articlesSaveNews.isFavorite) {
                articlesSaveNews.isFavorite = true
                viewModel.addNewsInArticles(articlesSaveNews)
                Toast.makeText(requireContext(), "Сохранено", Toast.LENGTH_SHORT)
                    .show()
            } else {
                articlesSaveNews.isFavorite = false
                viewModel.deleteFavouriteNews(articlesSaveNews)
                Toast.makeText(requireContext(), "Убрано из избранных", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
