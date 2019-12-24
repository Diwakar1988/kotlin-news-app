package com.github.diwakar1988.newsapi.ui.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.diwakar1988.newsapi.R
import com.github.diwakar1988.newsapi.core.BaseFragment
import com.github.diwakar1988.newsapi.dataclasses.Article
import com.github.diwakar1988.newsapi.dataclasses.NewsAPIResponse
import com.github.diwakar1988.newsapi.net.ApiClientCallback
import com.github.diwakar1988.newsapi.net.ApiFailure
import com.github.diwakar1988.newsapi.net.OkHttpApiClient
import com.github.diwakar1988.newsapi.ui.details.DetailsFragment
import com.github.diwakar1988.newsapi.utils.NavigationManager
import kotlinx.android.synthetic.main.fragment_news.*

private const val ARG_SECTION = "section"

class NewsListFragment : BaseFragment(), NewsListAdapter.OnNewsListItemClicked {

    companion object {
        @JvmStatic val TAG = "NewsListFragment"
        @JvmStatic
        fun newInstance(section: String) =
            NewsListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_SECTION, section)
                }
            }
    }
    lateinit var section:String;
    var page:Byte = 1;
    lateinit var adapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        section = arguments?.getString(ARG_SECTION)!!
        return inflater!!.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.rv_news_list.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        this.rv_news_list.setHasFixedSize(true)
        this.loadNews(page);
    }

    override fun setupToolbar() {
        //do nothing as this fragment is being used inside landing fragment
    }

    private fun loadNews(page:Byte) {
        showLoader()
        val category:String? = if(section.equals("Today")) null else section.toLowerCase()
        OkHttpApiClient.getHeadlines(country = "IN", category = category, page =page, callback = object :
            ApiClientCallback {
            override fun onResponse(data: NewsAPIResponse) {
                hideLoader()
                adapter =
                    NewsListAdapter(
                        data.articles,
                        this@NewsListFragment
                    );
                this@NewsListFragment.rv_news_list.adapter = adapter;
            }

            override fun onFailure(failure: ApiFailure) {
                hideLoader(true)
            }

        })
    }
    private fun showLoader(){
        this.rv_news_list.visibility = View.GONE
        this.progress_view.visibility = View.VISIBLE
    }
    private fun hideLoader(showError: Boolean = false){
        if (showError){
            Toast.makeText(activity,"Some error occurred, please try again",Toast.LENGTH_SHORT)
        }
        this.rv_news_list.visibility = View.VISIBLE
        this.progress_view.visibility = View.GONE
    }

    override fun onNewsListItemClicked(position: Int) {
        val article:Article = this.adapter.getItem(position)
        NavigationManager.add(DetailsFragment.newInstance(article),DetailsFragment.TAG);
    }
}