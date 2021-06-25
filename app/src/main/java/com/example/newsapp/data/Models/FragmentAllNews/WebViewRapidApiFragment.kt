package com.example.newsapp.data.Models.FragmentAllNews

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.newsapp.R
import com.example.newsapp.data.Models.RapidApiNews.Value
import tj.livo.newsapp.models.Articles

class WebViewRapidApiFragment : Fragment() {

    private lateinit var value : Value
    private lateinit var webView: WebView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_recent_history, container, false)
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (arguments?.getParcelable<Value>("article") != null) {
            value = arguments!!.getParcelable<Value>("article")!!
        }
        webView = view.findViewById(R.id.webView1)
        value.url?.let { webView.loadUrl(it) }


    }

}