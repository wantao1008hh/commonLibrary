package com.wan.commonsdk.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyItemDecoration(var space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
//        outRect.left = space
//        outRect.right = space
//        outRect.bottom = space
        if (parent.getChildAdapterPosition(view) != 0)
            outRect.top = space
    }
}