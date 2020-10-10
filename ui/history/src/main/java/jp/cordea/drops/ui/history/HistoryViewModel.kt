package jp.cordea.drops.ui.history

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import jp.cordea.drops.ui.NavigationMenuBindable

class HistoryViewModel @ViewModelInject constructor() : ViewModel(), NavigationMenuBindable {
    override fun onCatalogClick() {
    }

    override fun onHistoryClick() {
    }

    override fun onAccountClick() {
    }
}
