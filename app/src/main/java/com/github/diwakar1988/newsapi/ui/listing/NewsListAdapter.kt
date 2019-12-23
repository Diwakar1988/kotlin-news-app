package com.github.diwakar1988.newsapi.ui.listing;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.diwakar1988.newsapi.R
import com.github.diwakar1988.newsapi.dataclasses.Article
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class NewsListAdapter(val newsList:ArrayList<Article>,val onNewsListItemClicked: OnNewsListItemClicked): RecyclerView.Adapter<NewsListAdapter.NewsHolder>() {
    companion object{
        private const val VIEW_TYPE_LEFT:Int=1;
        private const val VIEW_TYPE_RIGHT:Int=2;
        val DATE_FORMATTER:SimpleDateFormat = SimpleDateFormat("EEE, d MMM yyyy hh:mm aaa")
        val DATE_PARSER:SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
    }

    override fun getItemViewType(position: Int): Int {
        return if(position%2==0) VIEW_TYPE_LEFT else VIEW_TYPE_RIGHT
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val resId = when(viewType){
            VIEW_TYPE_LEFT -> R.layout.row_news_left
            else->R.layout.row_news_right
        }
        return NewsHolder(
            LayoutInflater.from(parent.context).inflate(resId, parent, false),
            onNewsListItemClicked
        )
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun getItem(position: Int): Article{
        return newsList[position]
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val article = getItem(position)
        Picasso.get().load(article.urlToImage).fit().into(holder.image)
        holder.title.setText(article.title)
        holder.description.setText(article.description)
        holder.source.setText(String.format("Source: %s",article.source.name))
        holder.date.setText(
            DATE_FORMATTER.format(
                DATE_PARSER.parse(article.publishedAt)))
    }

    class NewsHolder(root:View, val onNewsListItemClicked: OnNewsListItemClicked):RecyclerView.ViewHolder(root), View.OnClickListener{
        var image:ImageView
        var date:TextView
        var title:TextView
        var description:TextView
        var source:TextView
        init {
            root.setOnClickListener(this)
            image = root.findViewById(R.id.image)
            date = root.findViewById(R.id.date)
            title = root.findViewById(R.id.title)
            description = root.findViewById(R.id.description)
            source = root.findViewById(R.id.source)

        }
        override fun onClick(view: View?) {
            onNewsListItemClicked.onNewsListItemClicked(adapterPosition);
        }

    }
    interface OnNewsListItemClicked{
        fun onNewsListItemClicked(position: Int)
    }
}
