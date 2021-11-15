package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class Adapter(val items: ArrayList<FeedItem>): BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    val inflater = LayoutInflater.from(parent!!.context)

        val view = convertView?:
        inflater.inflate(R.layout.list_item, parent, false)
        val vTitle = view.findViewById(R.id.item_title)

        val item = getItem(position) as FeedItem

        vTitle.text = item.title
        return view
    }
}