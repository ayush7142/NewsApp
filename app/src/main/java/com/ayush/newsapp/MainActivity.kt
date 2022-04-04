package com.ayush.newsapp

/*
    1. All retrofit callbacks that we want to insert in our app are defined in an interface.
    2. A Retrofit object is created in main activity to use those callback functions in our code
    3. Response obtained from server is in Json format, we convert it into java objects and use the data according to our need
 */

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNews()

    }

    private fun getNews() {
        val news = NewsService.newsInstance.getResponse("in", 1)
        news.enqueue(object: retrofit2.Callback<News>{

            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if(news != null) {
                    Log.d("CHECKTHIS", news.toString())
                }
                recyclerView = findViewById(R.id.recyclerView)
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    if (news != null) {
                        adapter = NewsAdapter(this@MainActivity, news.articles)
                    }
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("CHECKTHIS", "Error")
            }

        })
    }
}