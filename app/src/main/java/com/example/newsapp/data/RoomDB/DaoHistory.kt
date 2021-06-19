package com.example.newsapp.data.RoomDB

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsapp.data.Models.HistoryNewsEntity.ArticlesHistoryNews

@Dao
interface DaoHistory {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticles(articlesHistoryNews: ArticlesHistoryNews)

    @Query("select * from historyNews")
    fun getHistoryNews(): LiveData<List<ArticlesHistoryNews>>

    }