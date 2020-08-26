package com.binar.kotlinroom.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.binar.kotlinroom.db.ItemDatabase
import com.binar.kotlinroom.R
import com.binar.kotlinroom.db.Stuff
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_edit.btnSave


class AddActivity : AppCompatActivity(), AddPresenter.Listener {
    private lateinit var presenter: AddPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        ItemDatabase.getInstance(this)?.let {
            presenter= AddPresenter(it,this)
        }

        btnSave.setOnClickListener {
            val objectStuff = Stuff(null, etAddNameStuff.text.toString(), etAddQty.text.toString().toInt())
            presenter.saveItem(objectStuff)
        }
    }


    override fun showSavedSuccess(item: Stuff) {
        runOnUiThread {
            Toast.makeText(this@AddActivity, "Sukses menambahkan data${item.name}", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    override fun showSavedFailed(item: Stuff) {
        runOnUiThread {
            Toast.makeText(this@AddActivity, "Sukses menambahkan data${item.name}", Toast.LENGTH_LONG).show()
            finish()
        }

    }


}