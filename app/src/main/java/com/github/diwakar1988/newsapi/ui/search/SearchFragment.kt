package com.github.diwakar1988.newsapi.ui.search


import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import com.github.diwakar1988.newsapi.R
import com.github.diwakar1988.newsapi.core.BaseFragment
import com.github.diwakar1988.newsapi.ui.listing.NewsListFragment
import com.github.diwakar1988.newsapi.utils.Constants
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : BaseFragment(), View.OnClickListener {

    private lateinit var resultFragment:NewsListFragment;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_box.requestFocus()
        resultFragment = NewsListFragment.newInstance(Constants.STR_SEARCH);
        childFragmentManager.beginTransaction().add(R.id.child_fragment,resultFragment).commit()
        clear.setOnClickListener(this)
    }

    override fun setupToolbar() {
        toolbar.title = ""
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_primary_color)
        getBaseActivity().setSupportActionBar(toolbar);
        search_box.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun performSearch() {
        val query:String = search_box.text.toString().trim();
        if (TextUtils.isEmpty(query) || query.length<3){
            return
        }
        resultFragment.searchNews(query)
    }

    companion object {
        @JvmStatic val TAG = "SearchFragment"
        fun newInstance() = SearchFragment()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.clear -> search_box.setText("")
        }
    }
}
