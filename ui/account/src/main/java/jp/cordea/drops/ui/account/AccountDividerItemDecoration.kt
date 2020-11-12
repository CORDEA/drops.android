package jp.cordea.drops.ui.account

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

internal class AccountDividerItemDecoration(
    context: Context
) : RecyclerView.ItemDecoration() {
    private val divider = requireNotNull(
        ContextCompat.getDrawable(
            context,
            R.drawable.account_divider
        )
    )
    private val bounds = Rect()

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val view = parent.getChildAt(0) ?: return
        parent.getDecoratedBoundsWithMargins(view, bounds)
        divider.setBounds(
            0,
            bounds.bottom - divider.intrinsicHeight,
            view.width,
            bounds.bottom
        )
        divider.draw(c)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        if (position > 0) {
            return
        }
        outRect.set(0, 0, 0, divider.intrinsicHeight)
    }
}
