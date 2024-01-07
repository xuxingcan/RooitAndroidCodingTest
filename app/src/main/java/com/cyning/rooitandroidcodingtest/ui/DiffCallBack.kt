package com.cyning.rooitandroidcodingtest.ui

import androidx.recyclerview.widget.DiffUtil
import com.cyning.rooitandroidcodingtest.data.local.bean.Article

/**
 * use DiffUtil for better performance and animation
 */
class DiffCallBack(val oldList: List<Article>, val newList: List<Article>) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].url == newList[newItemPosition].url
    }

    //actually, there should only compare the filed which is shown
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldObj = oldList[oldItemPosition]
        val newObj = newList[oldItemPosition]
        return oldObj.url == newObj.url
                && oldObj.title == newObj.title
                && oldObj.author == newObj.author
                && oldObj.content == newObj.content
                && oldObj.description == newObj.description
                && oldObj.publishedAt == newObj.publishedAt
                && oldObj.source == newObj.source
                && oldObj.urlToImage == newObj.urlToImage
    }
}