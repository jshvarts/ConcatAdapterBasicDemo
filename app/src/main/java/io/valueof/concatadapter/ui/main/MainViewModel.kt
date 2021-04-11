package io.valueof.concatadapter.ui.main

import androidx.lifecycle.ViewModel
import io.valueof.concatadapter.data.DataRepository
import io.valueof.concatadapter.ui.main.model.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

class MainViewModel : ViewModel() {
    private val _itemList = MutableStateFlow<List<Item>>(emptyList())
    val itemList: StateFlow<List<Item>> = _itemList

    init {
        _itemList.value = DataRepository.getItems()
    }

    fun onItemSelected(id: String) {
        _itemList.value
            .first { it.id == id }.let {
                Timber.d("Item selected $it")
            }
    }
}