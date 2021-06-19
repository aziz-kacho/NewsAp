package com.example.newsapp.utils

import com.example.newsapp.data.Models.HistoryNewsEntity.ArticlesHistoryNews

interface OnClickHistory {
    fun onclickListener(articlesHistoryNews: ArticlesHistoryNews)
}