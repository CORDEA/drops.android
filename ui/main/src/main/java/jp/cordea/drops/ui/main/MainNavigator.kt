package jp.cordea.drops.ui.main

import jp.cordea.drops.ui.NavigationMenuNavigator

interface MainNavigator : NavigationMenuNavigator {
    fun navigateToCart()
    fun navigateToItem(id: String)
}
