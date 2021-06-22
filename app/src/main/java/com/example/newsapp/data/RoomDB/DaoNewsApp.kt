package com.example.newsapp.data.RoomDB

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tj.livo.newsapp.models.Articles


@Dao
interface DaoNewsApp{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun addArticles(articles: Articles)


    // этот метод вытащит кокретную запись по isFavourite
    @Query("select * from news where isFavorite = :favourite")
    fun getSavedNews(favourite: Boolean): LiveData<List<Articles>>

    //    этот метод вытащит все записи из таблицы articlesNews
    @Query("select * from news order by id desc")
    fun getAllArticlesNews(): LiveData<List<Articles>>


    @Query("select * from news order by id desc")
    fun getNewsForSearch() : DataSource.Factory<Int, Articles>


  @Query("select * from news where type = :category")
   fun getNewsByCategory(category : String) : LiveData<List<tj.livo.newsapp.models.Articles>>

    @Query("select * from news where id = :id")
    fun checkIfExist(id: Int) : LiveData<List<tj.livo.newsapp.models.Articles>>

    @Query("delete from news")
    fun deleteAllNews()

    @Query("select * from news where title like '%' || :searchQuery || '%'")
    fun searchDataBase(searchQuery: String) : LiveData<List<Articles>>

    @Query("delete from news where type = :category")
    fun orderByCategory(category : String)


//    @Query("select 10 ")
//    fun getPagedList() : LiveData<List<Articles>>
}
