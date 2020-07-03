package io.pikassa.sample.ext

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
Created by Denis Chornyy on 02,Июль,2020
All rights received.
 */
class SimpleItemDecoration(context: Context, space: Int = 10) : RecyclerView.ItemDecoration() {

    private val spaceInDp = dpToPx(context, space)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        outRect.left = spaceInDp
        outRect.right = spaceInDp
        outRect.bottom = spaceInDp
        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = spaceInDp
        }
    }

    private fun dpToPx(context: Context, dp: Int): Int {
        return (dp * context.resources.displayMetrics.density).toInt()
    }
}