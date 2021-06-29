package com.example.newsapp.data.RoomDB

import androidx.lifecycle.LiveData
import androidx.room.*
import tj.livo.newsapp.models.ArticlesSaveNews

@Dao
interface DaoFavourite {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticles(articlesSaveNews: ArticlesSaveNews)

    @Query("select * from favouritesNews")
    fun getFavouritesNews(): LiveData<List<ArticlesSaveNews>>

    @Delete
    fun delete(articlesSaveNews: ArticlesSaveNews)

    @Query("delete from favouritesNews")
    fun deleteAllNews()
}