package com.example.newsapp.ui.logout

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.utils.OnClick
import tj.livo.newsapp.models.Articles
import java.util.*

class AdapterAllLastNews(
    private val contextAllRecommended: Fragment,
    private var onClick: OnClick,
) : RecyclerView.Adapter<AdapterAllLastNews.LastNewsViewHolder>() {

    var sportApiNews: List<tj.livo.newsapp.models.Articles> = emptyList()

    fun setData(list: List<tj.livo.newsapp.models.Articles>) {
        sportApiNews = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastNewsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_all_rapidapi,
            parent, false)
        return LastNewsViewHolder(itemView)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: LastNewsViewHolder, position: Int) {
        val currentItem = sportApiNews[position]
        holder.titleTextAllRecommended.text = currentItem.title
        holder.descriptionTextAllRecommended.text = currentItem.description
        Glide.with(contextAllRecommended).load(currentItem.urlToImage).into(holder.titleImage)
        holder.dateAllRecommended.text = getDate(currentItem).toString()
        holder.itemView.setOnClickListener {
            onClick.onclickListener(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return sportApiNews.size
    }

    class LastNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleImage: ImageView = itemView.findViewById(R.id.imageAllRecommended)

        val titleTextAllRecommended: TextView = itemView.findViewById(R.id.titleTextAllRecommended)


        val descriptionTextAllRecommended: TextView =
            itemView.findViewById(R.id.descriptionTextAllRecommended)

        val dateAllRecommended: TextView = itemView.findViewById(R.id.dateAllRecommended)
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    fun getDate(articles: Articles): String? {
        val date: Date = Date()
        val format = "dd.MM.yyyy HH:mm"
        val simpleDateFormat = SimpleDateFormat(format)
        return simpleDateFormat.format(date)
    }

}