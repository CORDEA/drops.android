package jp.cordea.drops.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import jp.cordea.drops.ui.databinding.ExpandableToolbarBinding

class ExpandableToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding: ExpandableToolbarBinding =
        ExpandableToolbarBinding.inflate(LayoutInflater.from(context), this, true)

    private var currentMode = Mode.COLLAPSED

    init {
        binding.navigationIcon.setOnClickListener {
            switch(if (currentMode == Mode.COLLAPSED) Mode.EXPANDED_NAVIGATION else Mode.COLLAPSED)
        }
        binding.menuIcon.setOnClickListener {
            switch(if (currentMode == Mode.COLLAPSED) Mode.EXPANDED_MENU else Mode.COLLAPSED)
        }
    }

    fun setNavigationView(@LayoutRes id: Int) {
        binding.navigationMenu.layoutResource = id
    }

    fun setMenu(@DrawableRes id: Int, @LayoutRes content: Int) {
        binding.menuIcon.setImageResource(id)
        binding.menu.layoutResource = content
        binding.menuIcon.isVisible = true
    }

    private fun switch(next: Mode) {
        if (currentMode == next) {
            return
        }
        val enableMenu = binding.menuIcon.isVisible
        when (next) {
            Mode.EXPANDED_NAVIGATION -> {
                if (enableMenu) {
                    binding.menu.isVisible = false
                }
                binding.navigationMenu.isVisible = true
            }
            Mode.EXPANDED_MENU -> {
                if (!enableMenu) {
                    throw IllegalArgumentException()
                }
                binding.navigationMenu.isVisible = false
                binding.menu.isVisible = true
            }
            Mode.COLLAPSED -> {
                binding.navigationMenu.isVisible = false
                if (enableMenu) {
                    binding.menu.isVisible = false
                }
            }
        }
        currentMode = next
    }

    private enum class Mode {
        EXPANDED_NAVIGATION,
        EXPANDED_MENU,
        COLLAPSED
    }

    class ScrollingViewBehavior(
        context: Context,
        attrs: AttributeSet
    ) : CoordinatorLayout.Behavior<View>(context, attrs) {
        private val toolbarHeight = context.resources.getDimensionPixelSize(
            R.dimen.expandable_toolbar_min_height
        )

        override fun layoutDependsOn(
            parent: CoordinatorLayout,
            child: View,
            dependency: View
        ): Boolean = dependency is ExpandableToolbar

        override fun onDependentViewChanged(
            parent: CoordinatorLayout,
            child: View,
            dependency: View
        ): Boolean {
            if (dependency.height <= 0) {
                return false
            }
            val diff = dependency.height - toolbarHeight
            if (diff <= 0) {
                if (child.translationY > 0) {
                    child.translationY = 0f
                    return true
                }
                return false
            }
            child.translationY = diff.toFloat()
            return true
        }
    }
}
