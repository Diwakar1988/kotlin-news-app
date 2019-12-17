package com.github.diwakar1988.newsapi.net

interface ApiClient {

    fun getHeadlines(country: String? ="IN", category:String?, page:Byte? = 0, callback: ApiClientCallback)
    fun search(query:String, page:Byte? = 0,callback: ApiClientCallback)
}