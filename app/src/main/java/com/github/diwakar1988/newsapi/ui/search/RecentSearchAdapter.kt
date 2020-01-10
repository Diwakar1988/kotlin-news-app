package com.github.diwakar1988.newsapi.ui.search;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.diwakar1988.newsapi.R

class RecentSearchAdapter(val keywords:ArrayList<String>, val onRecentSearchListItemClicked: OnRecentSearchListItemClicked): RecyclerView.Adapter<RecentSearchAdapter.SearchKeywordHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchKeywordHolder {
        return SearchKeywordHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_news_left, parent, false),
            onRecentSearchListItemClicked
        )
    }

    override fun getItemCount(): Int {
        return keywords.size
    }

    fun getItem(position: Int): String{
        return keywords[position]
    }

    override fun onBindViewHolder(holder: SearchKeywordHolder, position: Int) {
        val keyword = getItem(position)
        holder.title.text = keyword
    }

    class SearchKeywordHolder(root:View, private val onRecentSearchListItemClicked: OnRecentSearchListItemClicked):RecyclerView.ViewHolder(root), View.OnClickListener{
        var title:TextView
        init {
            root.setOnClickListener(this)
            title = root.findViewById(R.id.title)

        }
        override fun onClick(view: View?) {
            onRecentSearchListItemClicked.onRecentSearchListItemClicked(adapterPosition);
        }

    }
    interface OnRecentSearchListItemClicked{
        fun onRecentSearchListItemClicked(position: Int)
    }
}
