package com.binar.kotlinroom.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.kotlinroom.R
import com.binar.kotlinroom.add.AddActivity
import com.binar.kotlinroom.db.ItemDatabase
import com.binar.kotlinroom.db.Stuff
import com.binar.kotlinroom.edit.EditActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), MainActivityPresenter.Listener {
    private lateinit var presenter : MainActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ItemDatabase.getInstance(this)?.let {
            presenter = MainActivityPresenter(it,this)
        }

        fabAdd.setOnClickListener {
            presenter.goToAddActivity()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.fetchData()
    }

    override fun showItemList(listItem:List<Stuff>){
        runOnUiThread {
            val adapter = StuffAdapter(listItem, presenter)
            recyclerView.layoutManager= LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter
        }
    }

    override fun goToAddActivity() {
        val intentToAddActivity = Intent(this, AddActivity::class.java)
        startActivity(intentToAddActivity)
    }

    override fun goToEditActivity(item: Stuff) {
        val intentToEditActivity = Intent(this, EditActivity::class.java)
        intentToEditActivity.putExtra("stuff",item)
        startActivity(intentToEditActivity)
    }

    override fun showDeletedSuccess(item: Stuff) {
        Toast.makeText(this,"Data ${item.name} sukses dihapus",Toast.LENGTH_LONG).show()
    }

    override fun showDeletedFailed(item: Stuff) {
        Toast.makeText(this,"Data ${item.name} gagal dihapus",Toast.LENGTH_LONG).show()

    }



}