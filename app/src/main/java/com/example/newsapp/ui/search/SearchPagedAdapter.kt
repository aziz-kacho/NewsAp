package com.example.newsapp.ui.search

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.utils.OnClick
import tj.livo.newsapp.models.Articles
import java.util.*
import kotlin.collections.ArrayList

class SearchPagedAdapter(
    private val contextAllRecommended: Fragment,
    private val onclick: OnClick
) :
    PagedListAdapter<Articles, SearchPagedAdapter.SearchViewHolder>(DIFF_CALLBACK) {


    var articles: List<Articles> = emptyList()


    fun setData(list: List<Articles>) {
        articles = list
        notifyDataSetChanged()
        Log.e("TAG", "setData: $articles")
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SearchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_business_news,
            parent, false
        )
        return SearchViewHolder(itemView)
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(
        holder: SearchViewHolder,
        position: Int,
    ) {
        val currentItem = articles[position]
        holder.titleTextAllRecommended.text = currentItem.title
        holder.descriptionTextAllRecommended.text = currentItem.description
        Glide.with(contextAllRecommended).load(currentItem.urlToImage).into(holder.titleImage)
        holder.dateAllRecommended.text = getDate(currentItem).toString()
        holder.itemView.setOnClickListener {
            onclick.onclickListener(currentItem)
        }
    }


//    override fun getItemCount(): Int {
//        return articles.size
//    }


    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Articles>() {
            // Concert details may have changed if reloaded from the database,
            // but ID is fixed.
            override fun areItemsTheSame(
                oldConcert: Articles,
                newConcert: Articles,
            ) = oldConcert.id == newConcert.id

//


            override fun areContentsTheSame(
                oldConcert: Articles,
                newConcert: Articles,
            ) = oldConcert == newConcert
//
        }
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    fun getDate(articles: Articles): String? {
        val date: Date = Date()
        val format = "dd.MM.yyyy HH:mm"
        val simpleDateFormat = SimpleDateFormat(format)
        return simpleDateFormat.format(date)
    }


    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleImage: ImageView = itemView.findViewById(R.id.imageAllCategoryNews)
        val titleTextAllRecommended: TextView = itemView.findViewById(R.id.titleTextAllCategoryNews)
        val descriptionTextAllRecommended: TextView =
            itemView.findViewById(R.id.descriptionTextAllCategoryNews)
        val dateAllRecommended: TextView = itemView.findViewById(R.id.dateCategoryNews)
    }

}




