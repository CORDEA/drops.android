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
import androidx.core.view.isInvisible
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
    private val behavior = Behavior()

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext get() = job + Dispatchers.Main.immediate

    private val nextState = Channel<State>()
    private var currentState = State.COLLAPSED

    lateinit var navigationMenu: View
    private var menu: View? = null

    init {
        binding.navigationIcon.setOnClickListener {
            nextState.offer(
                if (currentState == State.EXPANDED_NAVIGATION) {
                    State.COLLAPSED
                } else {
                    State.EXPANDED_NAVIGATION
                }
            )
        }
        binding.menuIcon.setOnClickListener {
            nextState.offer(
                if (currentState == State.EXPANDED_MENU) {
                    State.COLLAPSED
                } else {
                    State.EXPANDED_MENU
                }
            )
        }
    }

    fun inflateNavigationView(@LayoutRes id: Int) {
        binding.navigationMenu.layoutResource = id
        navigationMenu = binding.navigationMenu.inflate()
    }

    fun inflateMenu(@DrawableRes id: Int, @LayoutRes content: Int) {
        binding.menuIcon.setImageResource(id)
        binding.menu.layoutResource = content
        menu = binding.menu.inflate()
        binding.menuIcon.isVisible = true
    }

    suspend fun collapse() {
        switch(State.COLLAPSED)
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
        val menu = menu
        when (next) {
            State.EXPANDED_NAVIGATION -> {
                if (menu != null) {
                    menu.isInvisible = true
                }
                navigationMenu.isInvisible = false
                behavior.slide(this, navigationMenu.height)
            }
            State.EXPANDED_MENU -> {
                if (menu == null) {
                    throw IllegalArgumentException()
                }
                navigationMenu.isInvisible = true
                menu.isInvisible = false
                behavior.slide(this, menu.height)
            }
            State.COLLAPSED -> {
                behavior.slide(this, 0)
                navigationMenu.isInvisible = true
                if (menu != null) {
                    menu.isInvisible = true
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
        return parent.getDependents(this).firstOrNull()
    }

    private enum class State {
        EXPANDED_NAVIGATION,
        EXPANDED_MENU,
        COLLAPSED
    }

    private class Behavior : CoordinatorLayout.Behavior<ExpandableToolbar>() {
        companion object {
            private const val DURATION = 200L
        }

        suspend fun slide(toolbar: ExpandableToolbar, to: Int) {
            val layer = toolbar.findFrontLayer() ?: return
            return animateTo(layer, to.toFloat())
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

    class ScrollingViewBehavior(
        context: Context,
        attrs: AttributeSet
    ) : CoordinatorLayout.Behavior<View>(context, attrs) {
        override fun layoutDependsOn(
            parent: CoordinatorLayout,
            child: View,
            dependency: View
        ): Boolean = dependency is ExpandableToolbar
    }
}
