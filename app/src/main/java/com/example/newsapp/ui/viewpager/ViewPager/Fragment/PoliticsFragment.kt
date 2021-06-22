package com.example.newsapp.ui.viewpager.ViewPager.Fragment


import android.os.Bundle
import android.util.Log
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
import com.example.newsapp.ui.logout.AdapterAllCategoryNews
import com.example.newsapp.utils.OnClick
import com.example.newsapp.network.api.RetrofitInstance
import com.example.newsapp.ui.logout.AllViewModel
import kotlinx.android.synthetic.main.fragment_business.*
import kotlinx.android.synthetic.main.fragment_business.progress_circular
import kotlinx.android.synthetic.main.fragment_politics.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.livo.newsapp.models.Articles
import tj.livo.newsapp.models.NewsResponseSave


class PoliticsFragment : Fragment(), OnClick {


    private lateinit var viewModel: AllViewModel
    private var getNewsResponse = MutableLiveData<NewsResponseSave>()
    private lateinit var recyclerViewBusinessNews: RecyclerView
    private lateinit var adapterBusinessNews: AdapterAllCategoryNews


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_politics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(this).get(AllViewModel::class.java)
        recyclerViewBusinessNews = view.findViewById(R.id.politicsRecyclerView)
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerViewBusinessNews.layoutManager = layoutManager
        adapterBusinessNews = AdapterAllCategoryNews(this, this)
        recyclerViewBusinessNews.adapter = adapterBusinessNews

        businessNews()
        viewModel.getNewsByCategory("politics").observe(viewLifecycleOwner) {
            progress_circular.visibility = View.GONE
            adapterBusinessNews.setData(it)
        }

    }

    private fun businessNews() {
        RetrofitInstance().api().getListOfHeadLine(
            "politics",
        ).enqueue(object : Callback<NewsResponseSave> {
            override fun onResponse(
                call: Call<NewsResponseSave>,
                response: Response<NewsResponseSave>,
            ) {
                Log.e("Status", response.code().toString())
                if (response.isSuccessful) {
                    viewModel.deleteByCategory("politics")
                    /*
                    Здесь будет удаление из базы данных
                     */
                    response.body()?.articles?.forEach {
                        val articles = it

                        articles.type = "politics"
                        viewModel.addArticles(articles)
                    }
                }
            }

            override fun onFailure(call: Call<NewsResponseSave>, t: Throwable) {
                Log.e("ERROR", t.message.toString())
            }
        })
        getNewsResponse?.observe(viewLifecycleOwner, { data ->
            Log.e("debug", "get Politics News: ${data.articles}")
        })
    }


    override fun onclickListener(articles: Articles) {
        val bundle: Bundle = Bundle()
        bundle.putParcelable("article", articles)
        findNavController().navigate(R.id.allNewsFragment, bundle)
    }

}