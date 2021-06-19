package com.example.newsapp.utils

import tj.livo.newsapp.models.Articles

interface OnClick {
    fun onclickListener(articles: Articles)
}