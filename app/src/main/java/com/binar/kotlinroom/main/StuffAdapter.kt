package com.binar.kotlinroom.main

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.binar.kotlinroom.R
import com.binar.kotlinroom.db.ItemDatabase
import com.binar.kotlinroom.db.Stuff
import com.binar.kotlinroom.edit.EditActivity
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StuffAdapter(private val listStuff: List<Stuff>, private val presenter: MainActivityPresenter) :
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
           presenter.goToEditActivity(listStuff[position])
        }

        holder.itemView.ivDelete.setOnClickListener {
            presenter.deleteItem(listStuff[position])
        }
    }
}