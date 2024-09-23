package com.example.prakt221_eremeev_api

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import org.json.JSONObject

private lateinit var checkgenre:CheckBox
private lateinit var poisk:EditText
class search_books : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_books)
        checkgenre = findViewById(R.id.genreid)
    }

    fun Getresults() {
        checkgenre = findViewById(R.id.genreid)
        poisk = findViewById(R.id.searchbook)
        if (!checkgenre.isChecked) {
            val url = "https://openlibrary.org/search.json?q=${poisk.toString()}"
            val queue = Volley.newRequestQueue(this)
            val stringRequest = StringRequest(
                Request.Method.GET,
                url,
                { response ->
                    val obj = JSONObject(response)
                    val name = obj.getJSONObject("title")
                    val winds = obj.getJSONObject("author_name")
                    val subjectsJsonArray = obj.getJSONArray("subject")

                },
                {
                    Log.d("Mylog", "Volley error:$it")

                }
            )
            queue.add(stringRequest)
        } else {

            var sn = Snackbar.make(
                findViewById(R.id.root_layout),
                "Вы не ввели город",
                Snackbar.LENGTH_LONG
            )
            sn.setActionTextColor(Color.RED)
            sn.show()

        }
    }
}