package com.example.newsapp.ui.viewpager.ViewPager.Fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.ui.logout.AdapterAllCategoryNews
import androidx.navigation.fragment.findNavController
import com.example.newsapp.utils.OnClick
import com.example.newsapp.network.api.RetrofitInstance
import com.example.newsapp.ui.logout.AllViewModel
import kotlinx.android.synthetic.main.fragment_business.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.livo.newsapp.models.Articles
import tj.livo.newsapp.models.NewsResponseSave

class BusinessFragment : Fragment(), OnClick {
    private lateinit var viewModel: AllViewModel
    private var getNewsResponse = MutableLiveData<NewsResponseSave>()
    private lateinit var recyclerViewBusinessNews: RecyclerView
    private lateinit var adapterBusinessNews: AdapterAllCategoryNews

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_business, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AllViewModel::class.java)
        recyclerViewBusinessNews = view.findViewById(R.id.saveRecyclerView)
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerViewBusinessNews.layoutManager = layoutManager
        adapterBusinessNews = AdapterAllCategoryNews(this, this)
        recyclerViewBusinessNews.adapter = adapterBusinessNews





        businessNews()
        viewModel.getNewsByCategory("business").observe(viewLifecycleOwner) {
            progress_circular.visibility = View.GONE
            adapterBusinessNews.setData(it)
        }


    }

    private fun businessNews() {
        RetrofitInstance().api().getListOfHeadLine(
            "business",
        ).enqueue(object : Callback<NewsResponseSave> {
            override fun onResponse(
                call: Call<NewsResponseSave>,
                response: Response<NewsResponseSave>,
            ) {
                Log.e("Status", response.code().toString())
                if (response.isSuccessful) {
                    /*
                    Здесь будет удаление из базы данных
                     */viewModel.deleteByCategory("business")

                    response.body()?.articles?.forEach {
                        val articles = it

                        articles.type = "business"
                        viewModel.addArticles(articles)
                    }
                }
            }

            override fun onFailure(call: Call<NewsResponseSave>, t: Throwable) {
                Log.e("ERROR", t.message.toString())

            }
        })
        getNewsResponse.observe(viewLifecycleOwner, { data ->
            Log.e("debug", "get Politics News: ${data.articles}")
        })
    }

    override fun onclickListener(articles: Articles) {
        val bundle: Bundle = Bundle()
        bundle.putParcelable("article", articles)
        findNavController().navigate(R.id.allNewsFragment, bundle)
    }

}