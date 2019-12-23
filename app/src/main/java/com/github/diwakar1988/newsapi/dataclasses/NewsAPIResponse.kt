package com.github.diwakar1988.newsapi.dataclasses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsAPIResponse(
    val articles: ArrayList<Article>,
    val status: String,
    val totalResults: Int
):Parcelable
@Parcelize
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
):Parcelable
@Parcelize
data class Source(
    val name: String
):Parcelable
