package com.example.newsapp.data.Models.FragmentAllNews

import androidx.fragment.app.Fragment
//import com.example.newsapp.ui.chosen.SaveViewModel

class RecentHistoryFragment : Fragment() {
//    private lateinit var articles: Articles
//    private lateinit var webView: WebView
//    private lateinit var saveNewsButton: FloatingActionButton
////    private lateinit var viewModel: SaveViewModel
//    private lateinit var articlesSaveNews: ArticlesSaveNews
//    private lateinit var articlesHistoryNews: ArticlesHistoryNews
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View? {
//        return inflater.inflate(R.layout.fragment_recent_history, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        var bundle: Bundle = Bundle()
//        if (arguments != null) {
//            bundle = arguments as Bundle
//            if (bundle.getParcelable<ArticlesHistoryNews>("article") != null) {
//                articlesHistoryNews = bundle.getParcelable<ArticlesHistoryNews>("article")!!
//            }
//        }
//        webView = view.findViewById(R.id.webView1)
//        articlesHistoryNews.url?.let { webView.loadUrl(it) }
//
//        viewModel = ViewModelProvider(this).get(SaveViewModel::class.java)
//        viewModel.readAllData.observe(viewLifecycleOwner) {
//            it.reversed().forEach {
//                articlesHistoryNews.isFavorite = articlesHistoryNews.title == it.title
//            }
//            articles = Articles(
//                articlesHistoryNews.type,
//                articlesHistoryNews.isFavorite,
//                articlesHistoryNews.author,
//                articlesHistoryNews.title,
//                articlesHistoryNews.description,
//                articlesHistoryNews.url,
//                articlesHistoryNews.urlToImage,
//                articlesHistoryNews.publishedAt,
//                articlesHistoryNews.content
//            )
//        }
//    }

}