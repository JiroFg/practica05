package com.example.practica05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.practica05.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val queue = Volley.newRequestQueue(this)

        val url = "https://jsonplaceholder.typicode.com/users"
        val jsonObjectResquest = JsonArrayRequest(Request.Method.GET,
            url,
            null,
            {
                val items = mutableListOf<String>()
                for(i in 0 until it.length()){
                    val item = it.getJSONObject(i)
                    val aux = "Nombre: ${item.getString("name")} Email: ${item.getString("email")}"
                    //textView.append("Nombre: ${item.getString("name")} Email: ${item.getString("email")}")
                    items.add(aux)
                }
                val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,items)
                binding.listView.adapter = arrayAdapter
            },
            { Log.d("Error: ", it.toString()) }
        )
        queue.add(jsonObjectResquest)

        /* val url = "https://www.google.com"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                response ->  textView.text = "Response is: ${response.substring(0,500)}"
            },
            { textView.text = "Error: ${it}" })
        queue.add(stringRequest) */
    }
}