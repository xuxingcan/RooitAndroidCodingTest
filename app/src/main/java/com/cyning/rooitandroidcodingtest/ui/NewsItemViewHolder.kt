package com.cyning.rooitandroidcodingtest.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cyning.rooitandroidcodingtest.R

class NewsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val img = itemView.findViewById<ImageView>(R.id.img)
    val title = itemView.findViewById<TextView>(R.id.desc)
}