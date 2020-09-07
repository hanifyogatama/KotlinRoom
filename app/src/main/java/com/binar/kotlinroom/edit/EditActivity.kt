package com.binar.kotlinroom.edit


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.binar.kotlinroom.db.ItemDatabase
import com.binar.kotlinroom.R
import com.binar.kotlinroom.db.Stuff
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity(),EditPresenter.Listener {


    private lateinit var presenter: EditPresenter
    private lateinit var stuff: Stuff

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        ItemDatabase.getInstance(this)?.let{
          presenter = EditPresenter(it,this)
        }

        intent.getParcelableExtra<Stuff>("stuff")?.let {
            stuff = it
        }

        etEditNameStuff.setText(stuff.name)
        etEditQty.setText(stuff.quantity.toString())

        btnSave.setOnClickListener {
            stuff.apply {
                name = etEditNameStuff.text.toString()
                quantity = etEditQty.text.toString().toInt()
            }
            presenter.editItem(stuff)
        }

    }

    override fun showEditedSuccess(item: Stuff) {
        runOnUiThread {
            Toast.makeText(
                this@EditActivity,
                "sukses mengubah ${item.name}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun showEditedFailed(item: Stuff) {
        runOnUiThread {
            Toast.makeText(
                this@EditActivity,
                "sukses mengubah ${item.name}",
                Toast.LENGTH_LONG
            ).show()
        }
    }



}