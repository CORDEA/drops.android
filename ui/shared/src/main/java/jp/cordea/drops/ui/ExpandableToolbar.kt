package jp.cordea.drops.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
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
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume

class ExpandableToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), CoordinatorLayout.AttachedBehavior, CoroutineScope {
    private val binding: ExpandableToolbarBinding =
        ExpandableToolbarBinding.inflate(LayoutInflater.from(context), this, true)
    private val behavior = Behavior(context)

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext get() = job + Dispatchers.Main.immediate

    private val nextState = Channel<State>()
    private var currentState = State.COLLAPSED

    init {
        binding.navigationIcon.setOnClickListener {
            nextState.offer(
                if (currentState == State.COLLAPSED) State.EXPANDED_NAVIGATION else State.COLLAPSED
            )
        }
        binding.menuIcon.setOnClickListener {
            nextState.offer(
                if (currentState == State.COLLAPSED) State.EXPANDED_MENU else State.COLLAPSED
            )
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

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        job = SupervisorJob()
        launch {
            nextState.consumeEach {
                switch(it)
            }
        }
    }

    override fun onDetachedFromWindow() {
        job.cancel()
        super.onDetachedFromWindow()
    }

    override fun getBehavior(): CoordinatorLayout.Behavior<*> = behavior

    private suspend fun switch(next: State) {
        if (currentState == next) {
            return
        }
        val enableMenu = binding.menuIcon.isVisible
        when (next) {
            State.EXPANDED_NAVIGATION -> {
                if (enableMenu) {
                    binding.menu.isVisible = false
                }
                binding.navigationMenu.isVisible = true
                behavior.slideDown(this)
            }
            State.EXPANDED_MENU -> {
                if (!enableMenu) {
                    throw IllegalArgumentException()
                }
                binding.navigationMenu.isVisible = false
                binding.menu.isVisible = true
            }
            State.COLLAPSED -> {
                behavior.slideUp(this)
                binding.navigationMenu.isVisible = false
                if (enableMenu) {
                    binding.menu.isVisible = false
                }
            }
        }
        currentState = next
    }

    private fun findFrontLayer(): View? {
        val parent = parent
        if (parent !is CoordinatorLayout) {
            return null
        }
        val views = parent.getDependents(this)
        return views.firstOrNull { it is BackdropFrontLayer }
    }

    private enum class State {
        EXPANDED_NAVIGATION,
        EXPANDED_MENU,
        COLLAPSED
    }

    private class Behavior(context: Context) : CoordinatorLayout.Behavior<ExpandableToolbar>() {
        companion object {
            private const val DURATION = 200L
        }

        private val toolbarMinHeight = context.resources.getDimensionPixelSize(
            R.dimen.expandable_toolbar_min_height
        )

        suspend fun slideDown(toolbar: ExpandableToolbar) {
            val layer = toolbar.findFrontLayer() ?: return
            val diff = toolbar.height - toolbarMinHeight
            if (diff <= 0) {
                return
            }
            return animateTo(layer, diff.toFloat())
        }

        suspend fun slideUp(toolbar: ExpandableToolbar) {
            val layer = toolbar.findFrontLayer() ?: return
            return animateTo(layer, 0f)
        }

        private suspend fun animateTo(view: View, to: Float) {
            val animator = view.animate()
            return suspendCancellableCoroutine {
                it.invokeOnCancellation { animator.cancel() }
                animator
                    .translationY(to)
                    .setDuration(DURATION)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            if (it.isActive) {
                                it.resume(Unit)
                            }
                        }

                        override fun onAnimationCancel(animation: Animator?) {
                            if (it.isActive) {
                                it.resume(Unit)
                            }
                        }
                    })
                    .start()
            }
        }
    }
}
