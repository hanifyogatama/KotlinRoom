package com.binar.kotlinroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_edit.btnSave
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {

    var mDb: ItemDatabase? = null

    //private lateinit var db:ItemDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

//        database.getInstance(this)?.let{
//            db = it
//        }

        mDb = ItemDatabase.getInstance(this)

        btnSave.setOnClickListener {
            val objectStuff = Stuff(
                null,
                etAddNameStuff.text.toString(),
                etAddQty.text.toString().toInt()
            )
            GlobalScope.launch {
                val result = mDb?.itemDao()?.addItem(objectStuff)
                runOnUiThread{
                    if(result!=0.toLong()){
                        // success
                        Toast.makeText(this@AddActivity,"Sukses menambahkan data${objectStuff.name}",Toast.LENGTH_LONG).show()
                    } else{
                        Toast.makeText(this@AddActivity,"gagal menambahkan data ${objectStuff.name}",Toast.LENGTH_LONG).show()
                    }
                    finish()
                }

            }

        }
    }
}