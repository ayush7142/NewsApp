package com.ayush.newsapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

    // https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=bc1da02b8f8842d8b0b54892e15f9d98
    // https://newsapi.org/v2/everything?domains=wsj.com&apiKey=bc1da02b8f8842d8b0b54892e15f9d98
    const val BASE_URL = "https://newsapi.org/"
    const val API_KEY = "bc1da02b8f8842d8b0b54892e15f9d98"

interface NewsInterface {
    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getResponse(@Query("country")country: String, @Query("page") page: Int): Call<News>


}

//Singleton object

object NewsService {
    val newsInstance: NewsInterface
    init {  // retrofit object
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}