package com.example.newsapp.data.Models.FragmentAllNews

import androidx.fragment.app.Fragment
//import com.example.newsapp.ui.chosen.SaveViewModel


class ChosenNewsFragment : Fragment() {
//    private lateinit var articles: Articles
//    private lateinit var webView: WebView
//    private lateinit var saveNewsButton: FloatingActionButton
////    private lateinit var viewModel: SaveViewModel
//    private lateinit var articlesSaveNews: ArticlesSaveNews
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View? {
//        return inflater.inflate(R.layout.fragment_chosen_news, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        var bundle: Bundle = Bundle()
//        if (arguments != null) {
//            bundle = arguments as Bundle
//            articlesSaveNews = bundle.getParcelable<ArticlesSaveNews>("article")!!
//        }
//        webView = view.findViewById(R.id.webView)
//        articlesSaveNews.url?.let { webView.loadUrl(it) }
//
//        viewModel = ViewModelProvider(this).get(SaveViewModel::class.java)
//        saveNewsButton = view.findViewById(R.id.saveNews_button)
//        viewModel.readAllData.observe(viewLifecycleOwner) {
//            it.forEach {
//                articlesSaveNews.isFavorite = articlesSaveNews.title == it.title
//            }
//            articles = Articles(
//                articlesSaveNews.type,
//                articlesSaveNews.isFavorite,
//                articlesSaveNews.author,
//                articlesSaveNews.title,
//                articlesSaveNews.description,
//                articlesSaveNews.url,
//                articlesSaveNews.urlToImage,
//                articlesSaveNews.publishedAt,
//                articlesSaveNews.content
//            )
//        }
//
//        saveNewsButton.setOnClickListener {
//            if (articlesSaveNews.isFavorite) {
//                articlesSaveNews.isFavorite = true
//                viewModel.deleteFavouriteNews(articlesSaveNews)
//                Toast.makeText(requireContext(), "Убрано из сохранённых", Toast.LENGTH_SHORT)
//                    .show()
//            } else {
//                articlesSaveNews.isFavorite = false
//                viewModel.addNewsInArticles(articlesSaveNews)
//                Toast.makeText(requireContext(), "Сохранено", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }
//    }
}