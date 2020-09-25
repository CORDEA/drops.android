package jp.cordea.drops.ui.main

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val horizontalMargin =
        context.resources.getDimensionPixelSize(R.dimen.main_list_item_horizontal_margin)
    private val verticalMargin =
        context.resources.getDimensionPixelSize(R.dimen.main_list_item_vertical_margin)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: return
        val spanCount = (parent.layoutManager as GridLayoutManager).spanCount
        when {
            spanCount > position -> {
                outRect.left = horizontalMargin * 3
                outRect.right = horizontalMargin
            }
            itemCount - position <= spanCount -> {
                outRect.left = horizontalMargin
                outRect.right = horizontalMargin * 3
            }
            else -> {
                outRect.left = horizontalMargin
                outRect.right = horizontalMargin
            }
        }
        outRect.bottom = verticalMargin
        outRect.top = verticalMargin
    }
}
