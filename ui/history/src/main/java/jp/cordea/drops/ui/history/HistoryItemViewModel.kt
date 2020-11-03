package jp.cordea.drops.ui.history

sealed class HistoryItemViewModel

data class HistoryCompletedItemViewModel(
    val id: String,
    val imageUrl: String,
    val title: String,
    val body: String
) : HistoryItemViewModel()

data class HistoryInProgressItemViewModel(
    val id: String,
    val imageUrl: String,
    val title: String,
    val body: String
) : HistoryItemViewModel()
