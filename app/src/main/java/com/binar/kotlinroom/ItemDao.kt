package com.binar.kotlinroom

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update

interface ItemDao {
    @Insert(onConflict = REPLACE)
    // return long jumlah data yang berhasil disimpan
    fun addItem(item: Stuff) : Long

    @Query("SELECT * FROM Stuff")
    fun readAllItem() : List<Stuff>
    // list of entitynya, menggunakan list bisa terjemahkan ke array list

    @Update
    // int jumlah data yang berhasil di update
    fun updateItem(item: Stuff): Int
    // update data baru

    @Delete
    // int jumlah data yang berhasil di delete
    fun deleteItem(item: Stuff) : Int

}