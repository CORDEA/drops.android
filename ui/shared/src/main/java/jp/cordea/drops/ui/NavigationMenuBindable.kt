package jp.cordea.drops.ui

import jp.cordea.drops.ui.databinding.NavigationMenuBinding

interface NavigationMenuBindable {
    fun onCatalogClick()
    fun onHistoryClick()
    fun onAccountClick()
}

fun ExpandableToolbar.bindNavigationMenu(bindable: NavigationMenuBindable) {
    NavigationMenuBinding.bind(navigationMenu).bindable = bindable
}
