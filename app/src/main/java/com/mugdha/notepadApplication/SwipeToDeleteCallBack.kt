package com.mugdha.notepadApplication

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.mugdha.notepadApplication.adapterFiles.Adapter
import com.mugdha.notepadApplication.adapterFiles.DataObject.deleteData
import java.util.*
import kotlin.collections.ArrayList
abstract class SwipeToDeleteCallBack:ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val swipeFlag=ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return makeMovementFlags(0,swipeFlag)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

}