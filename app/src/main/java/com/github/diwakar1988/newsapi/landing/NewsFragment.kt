package com.github.diwakar1988.newsapi.landing

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.diwakar1988.newsapi.R
import com.github.diwakar1988.newsapi.dataclasses.NewsAPIResponse
import com.github.diwakar1988.newsapi.net.ApiClientCallback
import com.github.diwakar1988.newsapi.net.ApiFailure
import com.github.diwakar1988.newsapi.net.OkHttpApiClient
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {

    companion object {
        private val ARG_SECTION = "section"
        val TAG = "NewsFragment";
        fun newInstance(section: String): NewsFragment {
            val args = Bundle()
            args.putSerializable(ARG_SECTION, section)
            val fragment = NewsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var section:String;
    var page:Byte = 1;
    lateinit var adapter:NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        section = arguments?.getString(ARG_SECTION)!!
        Log.d(TAG, section)
        return inflater!!.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.rv_news_list.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        this.rv_news_list.setHasFixedSize(true)
        this.loadNews(page);
    }

    private fun loadNews(page:Byte) {
        this.showLoader()
        val category:String? = if(section.equals("Today")) null else section.toLowerCase()
        OkHttpApiClient.getHeadlines(country = "IN", category = category, page =page, callback = object :
            ApiClientCallback {
            override fun onResponse(data: NewsAPIResponse) {
                this@NewsFragment.hideLoader()
                this@NewsFragment.rv_news_list.adapter = NewsAdapter(data.articles)
            }

            override fun onFailure(failure: ApiFailure) {
                this@NewsFragment.hideLoader(true)
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
}