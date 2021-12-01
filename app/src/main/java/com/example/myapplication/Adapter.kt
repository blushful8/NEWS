package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewConfiguration.get
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.ActionBarPolicy.get
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.squareup.picasso.Picasso

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
        //val vTitle = view.findViewById(R.id.item_title)

        //val item = getItem(position) as FeedItem

        //vTitle.text = item.title
        return view
    }
}

class RecAdapter(val items: ArrayList<FeedItem>):RecyclerView.Adapter<RecHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return  RecHolder(view)
    }

    override fun onBindViewHolder(holder: RecHolder, position: Int) {
        val item = items[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

}

class RecHolder(view: View): RecyclerView.ViewHolder(view){
fun bind(item:FeedItem){
    val vTitle = itemView.findViewById<TextView>(R.id.item_title)
    var s:String? = null
    s = item.title
    
    vTitle.text = s

}
}