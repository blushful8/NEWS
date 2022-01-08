package com.example.myapplication


import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.time.format.DateTimeFormatter


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
    val i = Intent(Intent.ACTION_VIEW)

fun bind(item:FeedItem){
    i.putExtra(item.link, "link")
    val vTitle = itemView.findViewById<TextView>(R.id.item_title)
    val vDesc = itemView.findViewById<TextView>(R.id.item_description)
    val vPubDate = itemView.findViewById<TextView>(R.id.item_pubDate)
    val vEnclosure = itemView.findViewById<ImageView>(R.id.item_image)
    val img = "https://telekritika.ua/tk-static/2019/04/tsn_1604.jpg"
    val changeString:String = item.pubDate?.replaceBefore(' ', "").toString()
    vTitle.text = item.title?.replace("&amp;quot;", "\"")?.replace("&amp;#039;", "\'")
    vDesc.text = item.description
    vPubDate.text = changeString
    Picasso.with(vEnclosure.context).load(img).into(vEnclosure)

    itemView.setOnClickListener {
        i.data = Uri.parse(item.link)
        vEnclosure.context.startActivity(i)
    }
}
}