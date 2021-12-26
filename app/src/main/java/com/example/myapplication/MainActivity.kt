package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var recView: RecyclerView? = null
    var request: Disposable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val click = findViewById<FrameLayout>(R.id.frame)
        recView = findViewById(R.id.act1_recView)


            val o = createRequest("https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Ftsn.ua%2Frss%2Ffull.rss")
                .map { Gson().fromJson(it, Feed::class.java) }
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

            request = o.subscribe({
                                  showRecView(it.items)
                for(item in it.items)
                    Log.i("test1", "link: ${item.link}")
//                for (item in it.items)
//                    Log.w("test", "title: ${item.title}")
            },
                {
                    Log.e("TAG", "", it)
                })
        click?.setOnClickListener {
            Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
val i = Intent(this@MainActivity, ExtendNewsActivity::class.java)
            startActivity(i)
        }
    }



//    fun showLinearLayour(feedList: ArrayList<FeedItem>){
//        for (f in feedList){
//            val view = inflater.inflate(R.layout.list_item, vList, false)
//            val vTitle = view.findViewById<TextView>(R.id.item_title)
//            listView?.addView(view)
//        }
//    }

    fun showRecView(feedList: ArrayList<FeedItem>){
        recView?.adapter = RecAdapter(feedList)
        recView?.layoutManager = LinearLayoutManager(this)

    }
}
//"data": [
//        {
//            "title": "Новини Сніданку з 1+1",
//            "url": "https:\/\/1plus1.video\/novosti-snidanku-z-11",
//            "img": "https:\/\/images.1plus1.video\/playlist-1\/103660\/67a7b3721302f293166c49487ac7099a.220x330.jpg",
//            "pc": 0
//        },
//        {
//            "title": "Автоновини",
//            "url": "https:\/\/1plus1.video\/avtonovosti",
//            "img": "https:\/\/images.1plus1.video\/playlist-1\/103762\/51380fcaca9441314f3ad1855462e962.220x330.jpg",
//            "pc": 0
//        },