package jp.cordea.drops.ui.account

sealed class AccountItemViewModel

class AccountHeaderItemViewModel(
    val imageUrl: String,
    val name: String,
    val description: String
) : AccountItemViewModel()

class AccountListItemViewModel(
    val label: String,
    val body: String
) : AccountItemViewModel()
