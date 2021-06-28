package com.example.newsapp.ui.save_historyFragment

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
import com.example.newsapp.data.Models.HistoryNewsEntity.ArticlesHistoryNews
import com.example.newsapp.R
import com.example.newsapp.utils.OnClickHistory
import java.util.*

class HistoryNewsAdapter(private val contextAllRecommended: Fragment, private val onClickHistory: OnClickHistory) : RecyclerView.Adapter<HistoryNewsAdapter.HistoryNewsAdapterViewHolder>()  {

    var historyNews : List<ArticlesHistoryNews> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HistoryNewsAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_savednews,
            parent, false)
        return HistoryNewsAdapterViewHolder(itemView)
        }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: HistoryNewsAdapterViewHolder, position: Int) {
        val currentItem =   historyNews.get(position)
        holder.titleTextAllRecommended.text = currentItem.title
        holder.descriptionTextAllRecommended.text = currentItem.description
        Glide.with(contextAllRecommended).load(currentItem.urlToImage).into(holder.titleImage)
        holder.dateAllRecommended.text = getDate(currentItem).toString()
        holder.itemView.setOnClickListener {
            onClickHistory.onclickListener(currentItem)
        }
    }

    fun setData(list: List<ArticlesHistoryNews>){
        historyNews = list
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
    return historyNews.size
    }




    class HistoryNewsAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleImage: ImageView = itemView.findViewById(R.id.imageAllRecommended)
        val titleTextAllRecommended: TextView = itemView.findViewById(R.id.titleTextAllRecommended)
        val descriptionTextAllRecommended: TextView = itemView.findViewById(R.id.descriptionTextAllRecommended)
        val dateAllRecommended: TextView = itemView.findViewById(R.id.dateAllRecommended)
    }
    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    fun getDate(articles: ArticlesHistoryNews): String? {
        val date: Date = Date()
        val format = "dd.MM.yyyy HH:mm"
        val simpleDateFormat = SimpleDateFormat(format)
        return  simpleDateFormat.format(date)
    }

}