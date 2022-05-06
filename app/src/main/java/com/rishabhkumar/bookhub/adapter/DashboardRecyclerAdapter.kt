package com.rishabhkumar.bookhub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rishabhkumar.bookhub.R
import com.rishabhkumar.bookhub.model.Book
import org.w3c.dom.Text

class DashboardRecyclerAdapter(val context: Context,val itemList:ArrayList<Book> ) : RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        //responsible for making initial viewholders
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_dashboard_single_row,parent,false)
        return DashboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        //responsible for recycling and reusing of viewholders
//        val text = itemList[position]
//        holder.textView.text = text

        val book = itemList[position]
        holder.txtBookName.text = book.bookName
        holder.txtBookAuthor.text = book.bookAuthor
        holder.txtBookPrice.text = book.bookCost
        holder.txtRating.text = book.bookRating
//        holder.imgBookImage.setBackgroundResource(book.bookImage)

        holder.imgBookImage.setImageResource(book.bookImage)
    }

    override fun getItemCount(): Int {
        //method stores the total number of items of list
        return itemList.size
    }



    class DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val txtBookName : TextView = view.findViewById(R.id.txtBookName)
        val txtBookAuthor : TextView = view.findViewById(R.id.txtAuthorName)
        val txtBookPrice : TextView = view.findViewById(R.id.txtBookPrice)
        val txtRating : TextView = view.findViewById(R.id.txtRating)
        val imgBookImage : ImageView = view.findViewById(R.id.imgBook)

    }



}