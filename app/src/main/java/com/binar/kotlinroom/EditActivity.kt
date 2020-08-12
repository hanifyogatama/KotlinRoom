package com.binar.kotlinroom


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EditActivity : AppCompatActivity() {
    var mDb: ItemDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        mDb = ItemDatabase.getInstance(this)

        val objectItem = intent.getParcelableExtra<Stuff>("stuff")

        etEditNameStuff.setText(objectItem?.name)
        etEditQty.setText(objectItem!!.quantity)

        GlobalScope.async {
            val result = mDb?.itemDao()?.updateItem(objectItem)

            runOnUiThread {
                if (result != 0) {
                    Toast.makeText(
                        this@EditActivity,
                        "sukses mengubah ${objectItem.name}",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        this@EditActivity,
                        "gagal mengubah ${objectItem.quantity}",
                        Toast.LENGTH_LONG
                    ).show()
                }

                finish()
            }

        }

    }
}