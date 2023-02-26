package com.example.newsbuffet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest

class MainActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var mAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.recyclerView).layoutManager = LinearLayoutManager(this)

         fetchdata()
        mAdapter = NewsListAdapter(this)
        findViewById<RecyclerView>(R.id.recyclerView).adapter = mAdapter // linking recyclerView to adapter
    }

    fun fetchdata(){
        val url = "https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=7cbacef5a7014de1ae3d9ea1365e234a"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                  val newsJsonArray = it.getJSONArray("articles")
                  val newsArray = ArrayList<News>()
                  for (i in 0 until newsJsonArray.length()){
                     val newsJsonObject = newsJsonArray.getJSONObject(i)
                     val news = News(
                              newsJsonObject.getString("title"),
                              newsJsonObject.getString("author"),
                              newsJsonObject.getString("url"),
                              newsJsonObject.getString("urlToImage")
                     )
                    newsArray.add(news)
                }
                mAdapter.updateNews(newsArray)
            },
            {

            }

        )
        MySingletonn.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }



    override fun onItemClicked(item: News) {

    }


} 