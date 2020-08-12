package com.binar.kotlinroom

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.selects.whileSelect

class StuffAdapter(private val listStuff: List<Stuff>) :
    RecyclerView.Adapter<StuffAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listStuff.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvID.text = listStuff[position].id.toString()
        holder.itemView.tvNama.text = listStuff[position].name
        holder.itemView.tvQty.text = listStuff[position].quantity.toString()

        holder.itemView.ivEdit.setOnClickListener {
            val editActivityIntent = Intent(it.context, EditActivity::class.java)

            editActivityIntent.putExtra("stuff", listStuff[position])
            it.context.startActivity(editActivityIntent)
        }

        holder.itemView.ivDelete.setOnClickListener {
            AlertDialog.Builder(it.context).setPositiveButton("Ya") { p0, p1 ->
                val mDb = ItemDatabase.getInstance(holder.itemView.context)

                GlobalScope.async {
                    val result = mDb?.itemDao()?.deleteItem(listStuff[position])

                    (holder.itemView.context as MainActivity).runOnUiThread {
                        if (result != 0) {
                            Toast.makeText(
                                it.context,
                                "Data ${listStuff[position].name} berhasil ditambahkan",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Toast.makeText(
                                it.context,
                                "Data ${listStuff[position].name} gagal di tambahkan",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    (holder.itemView.context as MainActivity).fetchData()
                }

            }
                .setNegativeButton(
                    "Tidak"
                ) { p0, p1 ->
                    p0.dismiss()
                }
                .setMessage("Apakah Anda Yakin ingin menghapus data ${listStuff[position].name}")
                .setTitle("Konfirmasi Hapus").create().show()
        }


    }
}