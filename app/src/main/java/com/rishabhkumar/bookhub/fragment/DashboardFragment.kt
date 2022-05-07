package com.rishabhkumar.bookhub.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.rishabhkumar.bookhub.R
import com.rishabhkumar.bookhub.adapter.DashboardRecyclerAdapter
import com.rishabhkumar.bookhub.model.Book
import com.rishabhkumar.bookhub.util.ConnectionManager
import org.json.JSONException


class DashboardFragment : Fragment() {


    lateinit var recyclerDashboard : RecyclerView
    lateinit var layoutManager :RecyclerView.LayoutManager


    //static list of book names
//    val bookList = arrayListOf(
//        "Anna Karenina",
//        "Madame Bovary",
//        "War and Peace",
//        "The Great Gatsby",
//        "Lolita",
//        "Middlemarch",
//        "The Adventures of Huckleberry Finn",
//        "The Stories of Anton Chekhov",
//        "In Search of Lost Time",
//        "Hamlet"
//    )


    lateinit var recyclerAdapter : DashboardRecyclerAdapter


    //static list of book details
//    val bookInfoList = arrayListOf<Book>(
//        Book("Anna Karenina","Leo Tolstoy","Rs.249","4.5",R.drawable.image1),
//        Book("Madame Bovary","Gustave Flaubert","Rs.149","4.8",R.drawable.image2),
//        Book("War and Peace","Leo Tolstoy","Rs.199","3.9",R.drawable.image3),
//        Book("The Great Gatsby","F. Scott Fitzgerald","Rs.249","3.6",R.drawable.image4),
//        Book("Lolita","Vladimir Nabokov","Rs.199","4.1",R.drawable.image5),
//        Book("Middlemarch","George Eliot","Rs.349","4.6",R.drawable.image6),
//        Book("The Adventures of Huckleberry Finn","Mark Twain","Rs.249","4.4",R.drawable.image7),
//        Book("The Stories of Anton Chekhov","Anton Chekhov","Rs.299","4.6",R.drawable.image8),
//        Book("In Search of Lost Time","Marcel Proust","Rs.249","4.9",R.drawable.image9),
//        Book("Hamlet","William Shakespeare","Rs.399","4.9",R.drawable.image10),
//        )



    lateinit var btnCheckInternet : Button
    val bookInfoList = arrayListOf<Book>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        recyclerDashboard = view.findViewById(R.id.recyclerDashboard)

        layoutManager = LinearLayoutManager(activity)


        //to check whether app got connected with internet or not
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


        val queue = Volley.newRequestQueue(activity as Context)

        val url = "http://13.235.250.119/v1/book/fetch_books/"

        if(ConnectionManager().checkConnectivity(activity as Context)){
            val jsonObjectRequest = object : JsonObjectRequest(Request.Method.GET, url,null, Response.Listener{
                //here we will handle the response
                // println("Response is $it")
                try{
                    val sucsess = it.getBoolean("success")
                    if(sucsess){
                        val data = it.getJSONArray("data")
                        for(i in 0 until data.length()){
                            val bookJsonObject = data.getJSONObject(i)
                            val bookObject = Book(
                                bookJsonObject.getString("book_id"),
                                bookJsonObject.getString("name"),
                                bookJsonObject.getString("author"),
                                bookJsonObject.getString("rating"),
                                bookJsonObject.getString("price"),
                                bookJsonObject.getString("image")
                            )
                            bookInfoList.add(bookObject)
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
                        }
                    }else{
                        Toast.makeText(activity as Context,"Some error occured!!!",Toast.LENGTH_SHORT).show()
                    }

                }catch(e : JSONException){
                    Toast.makeText(activity as Context,"Some inexpected error occured!!!",Toast.LENGTH_SHORT).show()
                }
            },Response.ErrorListener {
                //here we will handle the errors
                //volley error handled here
                Toast.makeText(activity as Context,"Volley error occured",Toast.LENGTH_SHORT).show()
                //println("Error is $it")
            }){
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String,String>();
                    headers["Content-type"] = "application/json"
                    headers["token"] = "38574cd7e8eafc"
                    return headers
                }
            }

            queue.add(jsonObjectRequest)

        }else{
            val dialog = AlertDialog.Builder(activity as Context)
            dialog.setTitle("Failed")
            dialog.setMessage("Internet Connection not found")
            dialog.setPositiveButton("Open Settings"){
                    text,listener ->
                val settingIntent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingIntent)
                activity?.finish()
            }
            dialog.setNegativeButton("Exit"){
                    text,listener->
                ActivityCompat.finishAffinity(activity as Activity)//to close app
            }
            dialog.create()
            dialog.show()
        }

        return view
    }

}