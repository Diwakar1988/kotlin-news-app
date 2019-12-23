package com.github.diwakar1988.newsapi.ui.details


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.diwakar1988.newsapi.R
import com.github.diwakar1988.newsapi.dataclasses.Article
import com.github.diwakar1988.newsapi.ui.listing.NewsListAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*


private const val ARG_ARTICLE = "article"

class DetailsFragment : Fragment() {

    private var article: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            article = it.getParcelable(ARG_ARTICLE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load(article?.urlToImage).fit().into(banner)
        title.setText(article?.title)
        description.setText(article?.description)
        if (TextUtils.isEmpty(article?.author)) {
            source.setText(String.format("By: %s", article?.source?.name))
        } else {
            source.setText(String.format("By: %s, %s", article?.author, article?.source?.name))
        }

        date.setText(
            NewsListAdapter.DATE_FORMATTER.format(
                NewsListAdapter.DATE_PARSER.parse(article?.publishedAt)
            )
        )
        news.setText(article?.content)
        full_article.setText(String.format("Read More: %s", article?.url))
        full_article.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(article?.url))
            startActivity(browserIntent)
        }
    }

    companion object {
        @JvmStatic
        val TAG = "DetailsFragment"

        @JvmStatic
        fun newInstance(article: Article) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_ARTICLE, article)
                }
            }
    }
}
