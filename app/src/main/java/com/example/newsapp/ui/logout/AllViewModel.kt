package com.example.newsapp.ui.logout

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.newsapp.data.RoomDB.DataBase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tj.livo.newsapp.models.Articles

class AllViewModel(application: Application) : AndroidViewModel(application) {


    val daoNewsApp = DataBase.getInstance(application).daoNewsApp()
    val readAllData: LiveData<List<tj.livo.newsapp.models.Articles>> =
        daoNewsApp.getAllArticlesNews()



    fun addArticles(articles: tj.livo.newsapp.models.Articles) = GlobalScope.launch {
        daoNewsApp.addArticles(articles)
    }

    fun getSavedNews(favourite: Boolean) = GlobalScope.launch {
        daoNewsApp.getSavedNews(favourite)
    }

    fun getNewsByCategory(category: String): LiveData<List<tj.livo.newsapp.models.Articles>> {
        return daoNewsApp.getNewsByCategory(category)
    }

    fun deleteAllNews() = GlobalScope.launch {
        daoNewsApp.deleteAllNews()
    }

    fun searchDataBase(searchQuery: String): LiveData<List<Articles>> {
        return daoNewsApp.searchDataBase(searchQuery)
    }

    fun deleteByCategory(type: String) = GlobalScope.launch{
        daoNewsApp.orderByCategory(type)
    }
}