package jp.cordea.drops.ui

import android.content.Context
import androidx.annotation.StringRes
import dagger.Reusable
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@Reusable
class ResourceProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getString(@StringRes resId: Int, vararg args: Any) = context.getString(resId, *args)
}
