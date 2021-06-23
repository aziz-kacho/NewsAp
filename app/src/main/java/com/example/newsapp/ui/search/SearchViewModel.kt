package com.example.newsapp.ui.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.newsapp.data.RoomDB.DataBase
import tj.livo.newsapp.models.Articles

class SearchViewModel(application: Application) : AndroidViewModel(application) {


    private val daoNewsApp = DataBase.getInstance(application).daoNewsApp()
    private val getNewsForSearch: DataSource.Factory<Int, Articles> = daoNewsApp.getNewsForSearch()
    val getNewsSearchList: LiveData<PagedList<Articles>> = getNewsForSearch.toLiveData(50, 5)

}

