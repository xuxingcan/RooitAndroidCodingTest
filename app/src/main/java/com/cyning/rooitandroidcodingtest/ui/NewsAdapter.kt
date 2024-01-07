package com.cyning.rooitandroidcodingtest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cyning.rooitandroidcodingtest.R
import com.cyning.rooitandroidcodingtest.data.local.bean.Article

class NewsAdapter : RecyclerView.Adapter<NewsItemViewHolder>() {
    private val dates = ArrayList<Article>()

    fun update(list: List<Article>) {
        //use DiffUtil for better performance and animation
        DiffUtil.calculateDiff(DiffCallBack(dates, list), true).dispatchUpdatesTo(this)
        dates.clear()
        dates.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        return NewsItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.news_item, parent, false)
        )
    }

    override fun getItemCount() = dates.size

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val date = dates[position]
        Glide.with(holder.img).load(date.urlToImage).placeholder(R.mipmap.loading)
            .error(R.mipmap.error).into(holder.img)
        holder.title.text = date.title
    }
}