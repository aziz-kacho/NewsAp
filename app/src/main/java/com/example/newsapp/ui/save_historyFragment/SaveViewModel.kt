package com.example.newsapp.ui.save_historyFragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.newsapp.data.RoomDB.DataBase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tj.livo.newsapp.models.ArticlesSaveNews

class SaveViewModel(application: Application) : AndroidViewModel(application)  {
    val daoSaveNews  = DataBase.getInstance(application).daoFavourite()
    val readAllData : LiveData<List<ArticlesSaveNews>> = daoSaveNews.getFavouritesNews()


    fun addNewsInArticles(articlesSaveNews: ArticlesSaveNews) = GlobalScope.launch {
        Log.e("TAG", "addNewsInArticles: Ilhom")
        daoSaveNews.addArticles(articlesSaveNews)
    }

    fun deleteFavouriteNews(articlesSaveNews: ArticlesSaveNews) = GlobalScope.launch {
        daoSaveNews.delete(articlesSaveNews)
    }

    fun deleteAllNews() = GlobalScope.launch{
        daoSaveNews.deleteAllNews()
    }

}