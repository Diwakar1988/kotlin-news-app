package com.github.diwakar1988.newsapi.net

import com.github.diwakar1988.newsapi.dataclasses.NewsAPIResponse

data class ApiFailure(val code: Int?, val message: String?,val error: Throwable?)

interface ApiClientCallback {
    fun onResponse(data: NewsAPIResponse)
    fun onFailure(failure: ApiFailure)
}