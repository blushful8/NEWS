package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Completable.create
import io.reactivex.Flowable.create
import io.reactivex.Maybe.create
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.net.URI.create
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var text: TextView? = null
    var lLayout: LinearLayout? = null
    var listView: ListView? = null
    var recView: RecyclerView? = null
    var request: Disposable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        listView = findViewById<ListView>(R.id.act1_listView)

        val o = createRequest("https://api.1plus1.video/v2/ua/recommendation_projects/116485?cid=DaUfBFgt&vct=3&_t402324231520.json")
            .map {Gson().  }
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        request = o.subscribe({
            for(item in it.items)
        })

    }



//    fun showLinearLayour(feedList: ArrayList<FeedItem>){
//        for (f in feedList){
//            val view = inflater.inflate(R.layout.list_item, vList, false)
//            val vTitle = view.findViewById<TextView>(R.id.item_title)
//            listView?.addView(view)
//        }
//    }

    fun showListView(feedList: ArrayList<FeedItem>){
        listView?.adapter = Adapter(feedList)
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