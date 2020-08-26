package com.binar.kotlinroom.edit

import android.widget.Toast
import com.binar.kotlinroom.add.AddPresenter
import com.binar.kotlinroom.db.ItemDatabase
import com.binar.kotlinroom.db.Stuff
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditPresenter(val mDb: ItemDatabase, private val listener: Listener) {

    fun editItem(item: Stuff) {
        GlobalScope.launch {
            val result = mDb.itemDao().updateItem(item)

            if (result > 0) {
                listener.showEditedSuccess(item)
            } else {
                listener.showEditedFailed(item)
            }
        }
    }


    interface Listener {
        fun showEditedSuccess(item: Stuff)
        fun showEditedFailed(item: Stuff)
    }

}