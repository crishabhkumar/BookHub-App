package com.rishabhkumar.bookhub.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rishabhkumar.bookhub.R
import com.rishabhkumar.bookhub.adapter.DashboardRecyclerAdapter
import com.rishabhkumar.bookhub.model.Book
import com.rishabhkumar.bookhub.util.ConnectionManager


class DashboardFragment : Fragment() {


    lateinit var recyclerDashboard : RecyclerView
    lateinit var layoutManager :RecyclerView.LayoutManager

    val bookList = arrayListOf(
        "Anna Karenina",
        "Madame Bovary",
        "War and Peace",
        "The Great Gatsby",
        "Lolita",
        "Middlemarch",
        "The Adventures of Huckleberry Finn",
        "The Stories of Anton Chekhov",
        "In Search of Lost Time",
        "Hamlet"
    )


    lateinit var recyclerAdapter : DashboardRecyclerAdapter

    val bookInfoList = arrayListOf<Book>(
        Book("Anna Karenina","Leo Tolstoy","Rs.249","4.5",R.drawable.image1),
        Book("Madame Bovary","Gustave Flaubert","Rs.149","4.8",R.drawable.image2),
        Book("War and Peace","Leo Tolstoy","Rs.199","3.9",R.drawable.image3),
        Book("The Great Gatsby","F. Scott Fitzgerald","Rs.249","3.6",R.drawable.image4),
        Book("Lolita","Vladimir Nabokov","Rs.199","4.1",R.drawable.image5),
        Book("Middlemarch","George Eliot","Rs.349","4.6",R.drawable.image6),
        Book("The Adventures of Huckleberry Finn","Mark Twain","Rs.249","4.4",R.drawable.image7),
        Book("The Stories of Anton Chekhov","Anton Chekhov","Rs.299","4.6",R.drawable.image8),
        Book("In Search of Lost Time","Marcel Proust","Rs.249","4.9",R.drawable.image9),
        Book("Hamlet","William Shakespeare","Rs.399","4.9",R.drawable.image10),

        )



    lateinit var btnCheckInternet : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        recyclerDashboard = view.findViewById(R.id.recyclerDashboard)

        layoutManager = LinearLayoutManager(activity)

        recyclerAdapter = DashboardRecyclerAdapter(activity as Context,bookInfoList)


        recyclerDashboard.adapter = recyclerAdapter

        recyclerDashboard.layoutManager = layoutManager


        //function to make row separation between items
        recyclerDashboard.addItemDecoration(
            DividerItemDecoration(
                recyclerDashboard.context,
                (layoutManager as LinearLayoutManager).orientation
            )
        )

        btnCheckInternet = view.findViewById(R.id.btnCheckInternet)
        btnCheckInternet.setOnClickListener {
            if(ConnectionManager().checkConnectivity(activity as Context)){
                //internet is available
                val dialog = AlertDialog.Builder(activity as Context)
                dialog.setTitle("Success")
                dialog.setMessage("Internet Connection found")
                dialog.setPositiveButton("Ok"){
                    text,listener ->
                    //do nothing
                }
                dialog.setNegativeButton("Cancel"){
                    text,listener->
                    //do nothing
                }
                dialog.create()
                dialog.show()

            }else{
                //internet is not available
                val dialog = AlertDialog.Builder(activity as Context)
                dialog.setTitle("Failed")
                dialog.setMessage("Internet Connection not found")
                dialog.setPositiveButton("Ok"){
                        text,listener ->
                    //do nothing
                }
                dialog.setNegativeButton("Cancel"){
                        text,listener->
                    //do nothing
                }
                dialog.create()
                dialog.show()
            }
        }

        return view
    }

}