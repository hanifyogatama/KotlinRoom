package com.binar.kotlinroom.add

import com.binar.kotlinroom.db.ItemDatabase
import com.binar.kotlinroom.db.Stuff
import com.binar.kotlinroom.main.MainActivityPresenter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddPresenter(val mDb: ItemDatabase, private val listener: Listener) {

    fun saveItem(item: Stuff) {
        GlobalScope.launch {
            val result = mDb?.itemDao()?.addItem(item)

            if (result > 0) {
                listener.showSavedSuccess(item)
            } else {
                listener.showSavedFailed(item)
            }

        }

    }

    interface Listener {
        fun showSavedSuccess(item: Stuff)
        fun showSavedFailed(item: Stuff)
    }
}



