package jp.cordea.drops.ui.account

sealed class AccountItemViewModel

data class AccountHeaderItemViewModel(
    val imageUrl: String,
    val name: String,
    val description: String
) : AccountItemViewModel()

data class AccountListItemViewModel(
    val label: String,
    val body: String
) : AccountItemViewModel()
