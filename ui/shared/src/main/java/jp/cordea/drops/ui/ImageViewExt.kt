package jp.cordea.drops.ui

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter

@BindingAdapter("imageResource")
fun ImageView.setImageResourceExt(@DrawableRes resId: Int) {
    setImageResource(resId)
}
