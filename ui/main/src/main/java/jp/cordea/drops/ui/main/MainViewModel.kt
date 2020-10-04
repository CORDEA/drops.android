package jp.cordea.drops.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import jp.cordea.drops.ui.NavigationMenuBindable

class MainViewModel @ViewModelInject constructor() : ViewModel(), NavigationMenuBindable {
    override fun onCatalogClick() {
    }

    override fun onHistoryClick() {
    }

    override fun onAccountClick() {
    }
}
