package jp.cordea.drops

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import jp.cordea.drops.ui.Navigator

@Module
@InstallIn(FragmentComponent::class)
interface FragmentModule {
    @Binds
    fun bindNavigator(navigatorImpl: NavigatorImpl): Navigator
}
