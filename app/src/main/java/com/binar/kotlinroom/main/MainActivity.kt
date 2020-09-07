package com.binar.kotlinroom.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import org.json.JSONArray
import org.json.JSONObject

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

//        val objectJson = JSONObject()
//        val jsonArrayListClub = JSONArray()
//        jsonArrayListClub.put(
//            JSONObject()
//                .put("namaClub","persib")
//                .put("stadion","Si jalak harupat")
//                .put("peringkatLiga",1)
//        )
//
//        jsonArrayListClub.put(
//            JSONObject()
//                .put("namaClub","persija")
//                .put("stadion","BMW Stadion")
//                .put("peringkatLiga",2)
//        )
//
//        objectJson.put("timNasional","Indonesia")
//        objectJson.put("federasi","PSSI")
//        objectJson.put("peringkatFifa",169)
//        objectJson.put("isActive",true)
//        objectJson.put("stadion","Gelora bung karno")
//        objectJson.put("juaraAff",JSONObject.NULL)
//        objectJson.put("juaraSeaGames",2)
//        objectJson.put("listClub",jsonArrayListClub)
//
//        Log.d("jsonBinar",objectJson.toString())
//
//        objectJson.getJSONArray("listClub").getJSONObject(1).getString("namaClub")


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