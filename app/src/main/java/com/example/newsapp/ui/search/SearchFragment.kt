package com.example.newsapp.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.api.RetrofitInstanceRapidApi
import com.example.newsapp.data.Models.RapidApiNews.GetNews
import com.example.newsapp.data.Models.RapidApiNews.Value
import com.example.newsapp.network.searchRapidApi.RetrofitInstanceSearchRapidApi
import com.example.newsapp.ui.logout.AllViewModel
import com.example.newsapp.utils.OnClickRapidApi
import kotlinx.android.synthetic.main.fragment_recent.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tj.livo.newsapp.models.Articles

class SearchFragment : Fragment(), OnClickRapidApi {
    private lateinit var recyclerViewSearchNews: RecyclerView

    private lateinit var viewModel: AllViewModel
    private lateinit var articles: Articles
    private var valueList: List<Value> = emptyList()
    private lateinit var progressBar: ProgressBar
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var adapterSearch: SearchPagedAdapter
    private var getNewsResponse = MutableLiveData<GetNews>()
    private val listOfNews = ArrayList<Value>()



    private var REQUEST_SENDED = false
    private var q: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_recent, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(AllViewModel::class.java)
        progressBar = view.findViewById(R.id.progress_circular)


        val searchViewModel: SearchViewModel by viewModels()

        recyclerViewSearchNews = view.findViewById(R.id.searchRecyclerView)

        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        recyclerViewSearchNews.layoutManager = layoutManager
        adapterSearch = SearchPagedAdapter(this, this)
        recyclerViewSearchNews.adapter = adapterSearch


        var index: Int = 1
        rapidApiNews(index)

//        listOfNews.observe(viewLifecycleOwner) {
//            adapterSearch.setData(it)
//            progressBar.visibility = View.GONE
//            Log.d("TAG", "get RapidApiNews: $it")
//        }


        recyclerViewSearchNews.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                val totalItemCount: Int = layoutManager.itemCount
                val lastVisible: Int = layoutManager.findLastVisibleItemPosition()
                val endHasBeenReached: Boolean = lastVisible + 1 >= totalItemCount

                if (totalItemCount > 0 && endHasBeenReached) {
                    if (!REQUEST_SENDED) {
                        index++
                        rapidApiNews(index)



                        progressBar.visibility = View.VISIBLE
                        REQUEST_SENDED = true
                    }
                }
            }
        })









        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                filter: CharSequence?,
                start: Int,
                count: Int,
                after: Int,
            ) {

            }

            override fun onTextChanged(filter: CharSequence?, start: Int, before: Int, count: Int) {
//                filterList(filter.toString())

                searchRapidApi(filter.toString())
                Log.e("TAG", "onTextChanged: OK", )

            }


            override fun afterTextChanged(filter: Editable?) {

            }
        })


    }


    private fun filterList(filter: String) {

        val tempList: MutableList<Value> = ArrayList()

        for (d in valueList) {
            if (filter in d.title.toString()) {
                tempList.add(d)
            }
        }




//        viewModel.searchDataBase(filter).observe(viewLifecycleOwner) {
//            adapterSearch.setData(it)
//            progressBar.visibility = View.GONE
////            Log.e("TAG", "onQueryTextSubmit: $it")
//        }


//        listOfNews.observe(viewLifecycleOwner, {
//
//            adapterSearch.setData(it)
//            progressBar.visibility = View.GONE
//            Log.d("TAG", "get RapidApiNews: $it")
//        })


    }


    private fun rapidApiNews(index: Int) {
        RetrofitInstanceRapidApi().api().getListOfNews(
            index
        ).enqueue(object : Callback<GetNews> {
            override fun onResponse(
                call: Call<GetNews>,
                response: Response<GetNews>,
            ) {
                if (!response.isSuccessful) return

                if (response.body() != null) {
                    val list = response.body()!!.value
                    listOfNews.addAll(list)
                    adapterSearch.setData(listOfNews)
                    REQUEST_SENDED = false
                    progressBar.visibility = View.GONE
                }

                Log.e("Status", response.code().toString())


            }

            override fun onFailure(call: Call<GetNews>, t: Throwable) {
                Log.e("ERROR", t.message.toString())
            }

        })


        getNewsResponse.observe(viewLifecycleOwner, {
            Log.e("TAG", "get RapidApiNews: ${it.value}")

        })
    }


    private fun searchRapidApi(q: String) {
        RetrofitInstanceSearchRapidApi().api().getListOfNews(
            q,
            1
        ).enqueue(object : Callback<GetNews> {
            override fun onResponse(call: Call<GetNews>, response: Response<GetNews>) {
                if (!response.isSuccessful) return

                if (response.body() != null) {
                    val list = response.body()!!.value
                    listOfNews.addAll(list)
                    adapterSearch.setData(listOfNews)
                    REQUEST_SENDED = false
                    progressBar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<GetNews>, t: Throwable) {
                Log.e("ERROR", t.message.toString())
            }

        })
        Log.e("TAG", "searchRapidApi: ")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)


    }

    override fun onClickListener(value: Value) {

        val bundle: Bundle = Bundle()
        bundle.putParcelable("article", value)
        findNavController().navigate(R.id.webViewRapidApiFragment, bundle)
    }


}