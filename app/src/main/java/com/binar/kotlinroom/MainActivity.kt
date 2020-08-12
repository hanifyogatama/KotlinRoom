package com.binar.kotlinroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var mDb: ItemDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDb = ItemDatabase.getInstance(this)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        fetchData()

        fabAdd.setOnClickListener {
            val toAddActivity = Intent(this, AddActivity::class.java)
            startActivity(toAddActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    fun fetchData(){
        GlobalScope.launch {
            val listItem = mDb?.itemDao()?.readAllItem()

            runOnUiThread{
                listItem?.let{
                    val adapter = StuffAdapter(it)
                    recyclerView.adapter= adapter
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ItemDatabase.destroyInstance()
    }


}