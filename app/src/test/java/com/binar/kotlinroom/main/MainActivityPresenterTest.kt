package com.binar.kotlinroom.main

import android.content.ClipData
import com.binar.kotlinroom.db.ItemDatabase
import com.binar.kotlinroom.db.Stuff
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainActivityPresenterTest {

    @Mock
    lateinit var listener:MainActivityPresenter.Listener
    lateinit var presenter: MainActivityPresenter

    @Mock
    lateinit var mDb : ItemDatabase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainActivityPresenter(mDb,listener)
    }

    @Test
    fun goToAddActivity() {
        presenter.goToAddActivity()
        Mockito.verify(listener).goToAddActivity()
    }

    @Test
    fun goToEditActivity() {
        val stuf = Stuff(null,"Test",1)
        presenter.goToEditActivity(stuf)
        Mockito.verify(listener).goToEditActivity(stuf)
    }

//    @Test
//    fun deleteItemFailed() {
//        val stuff = Stuff(99,"test",1)
//        presenter.deleteItem(stuff)
//        Mockito.verify(listener).showDeletedSuccess(stuff)
//    }

}