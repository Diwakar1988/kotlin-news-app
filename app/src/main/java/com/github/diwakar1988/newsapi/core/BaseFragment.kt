package com.github.diwakar1988.newsapi.core

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

open abstract class BaseFragment:Fragment() {
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context !is BaseActivity){
            throw IllegalStateException("This fragment should be attached only with BaseActivity orr its child classes")
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
    }

    abstract fun setupToolbar()

    fun getBaseActivity():BaseActivity{
        return activity as BaseActivity
    }
}