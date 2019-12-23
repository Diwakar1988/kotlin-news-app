package com.github.diwakar1988.newsapi.net

import android.text.TextUtils
import com.github.diwakar1988.newsapi.dataclasses.NewsAPIResponse
import com.github.diwakar1988.newsapi.ui.landing.utils.Async
import com.github.diwakar1988.newsapi.utils.URLConstants
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

object OkHttpApiClient : ApiClient {

    private var client: OkHttpClient
    private var defaultHeaders: Headers
    private var gson: Gson

    init {
        client = OkHttpClient()
        defaultHeaders = Headers.headersOf("X-Api-Key", "319ea8b014ae48d1a60504c643639628")
        gson = Gson();
    }

    private class OkHttpCallback(var callback: ApiClientCallback): Callback{
        override fun onFailure(call: Call, e: IOException) {
            onError(null, null, e)
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                try {
                    val newsApiData: NewsAPIResponse = gson.fromJson<NewsAPIResponse>(response.body?.string(),NewsAPIResponse::class.java)
                    Async.runOnUi(Runnable {
                        callback.onResponse(newsApiData);
                    })
                }catch (ex:Exception){
                    onError(null, null, ex)
                }

            } else {
                onError(response.code, response.message, null)
            }
        }
        private fun onError(code: Int?, message: String?, error: Throwable?){
            Async.runOnUi(Runnable {
                callback.onFailure(ApiFailure(code, message, error))
            })
        }
    }

    override fun getHeadlines(
        country: String?,
        category: String?,
        page: Byte?,
        callback: ApiClientCallback
    ) {

        val headers = defaultHeaders.newBuilder().build()
        var request:Request
        if (TextUtils.isEmpty(category)){
            request = Request
                .Builder()
                .url("${URLConstants.HEADLINES}?country=$country&page=$page")
                .headers(headers)
                .build()
        }else{
            request = Request
                .Builder()
                .url("${URLConstants.HEADLINES}?country=$country&category=$category&page=$page")
                .headers(headers)
                .build()
        }
        client.newCall(request).enqueue(OkHttpCallback(callback))
    }

    override fun search(query: String, page: Byte?, callback: ApiClientCallback) {
        val headers = defaultHeaders.newBuilder().build()
        val request = Request
            .Builder()
            .url("${URLConstants.SEARCH}?query=$query&page=$page")
            .headers(headers)
            .build()
        client.newCall(request).enqueue(OkHttpCallback(callback))
    }
}