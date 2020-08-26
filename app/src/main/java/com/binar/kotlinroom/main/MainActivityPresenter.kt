package com.binar.kotlinroom.main

import com.binar.kotlinroom.db.ItemDatabase
import com.binar.kotlinroom.db.Stuff
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivityPresenter(val mDb : ItemDatabase, private val listener : Listener ) {
    fun fetchData(){
        GlobalScope.launch {
            val listItem = mDb.itemDao()?.readAllItem()
            listener.showItemList(listItem)
        }
    }

    fun goToAddActivity(){
        listener.goToAddActivity()
    }

    fun goToEditActivity(item: Stuff){
        listener.goToEditActivity(item)
    }

    fun deleteItem(item:Stuff){
        GlobalScope.launch {
            val totalRowDeleted = mDb.itemDao().deleteItem(item)
            if(totalRowDeleted > 0){
                listener.showDeletedSuccess(item)
            }else{
                listener.showDeletedFailed(item)
            }
        }
    }

    interface Listener{
        fun showItemList(listItem: List<Stuff>)
        fun goToAddActivity()
        fun goToEditActivity(item: Stuff)
        fun showDeletedSuccess(item: Stuff)
        fun showDeletedFailed(item: Stuff)
    }


}