package com.example.newsapp.utils

import tj.livo.newsapp.models.ArticlesSaveNews

interface OnClickFavorites {
    fun onclickListener(articlesSaveNews: ArticlesSaveNews)
}