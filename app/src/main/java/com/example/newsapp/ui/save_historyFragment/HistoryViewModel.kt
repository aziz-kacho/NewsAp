package com.example.newsapp.ui.save_historyFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.newsapp.data.Models.HistoryNewsEntity.ArticlesHistoryNews
import com.example.newsapp.data.RoomDB.DataBase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    val daoHistoryNews  = DataBase.getInstance(application).daoHistory()
    val readAllHistoryNews : LiveData<List<ArticlesHistoryNews>> = daoHistoryNews.getHistoryNews()

    fun addNewsInArticles(articlesHistoryNews: ArticlesHistoryNews) = GlobalScope.launch {
        daoHistoryNews.addArticles(articlesHistoryNews)
    }
}