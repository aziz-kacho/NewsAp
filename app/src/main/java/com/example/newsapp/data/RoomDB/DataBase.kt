package com.example.newsapp.data.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.data.Models.HistoryNewsEntity.ArticlesHistoryNews
import tj.livo.newsapp.models.ArticlesSaveNews

@Database(entities = [tj.livo.newsapp.models.Articles::class, ArticlesSaveNews::class, ArticlesHistoryNews::class],
    version = 37,
    exportSchema = true)
abstract class DataBase : RoomDatabase() {


    abstract fun daoNewsApp(): DaoNewsApp
    abstract fun daoFavourite(): DaoFavourite
    abstract fun daoHistory(): DaoHistory

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null
        fun getInstance(context: Context): DataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DataBase::class.java,
                        "note_data_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
                return instance
            }
        }
    }

}