package com.example.shopping.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopping.clickListeners.ItemClickListener
import com.example.shopping.data.model.Item
import com.example.shopping.data.repository.ItemRepositoryInterface

class ListViewModel(private val repository: ItemRepositoryInterface) : ViewModel(), ItemClickListener {

    private var _itemLiveData = MutableLiveData<List<Item>>()

    val itemsLiveData : LiveData<List<Item>>
    get() {
        loadItemData()
        return _itemLiveData
    }


    private fun loadItemData() {
        _itemLiveData.value = repository.list()
    }

    override fun onDelete(item: Item) {
        repository.delete(item)
        loadItemData()
    }

    override fun onEdit(item: Item) {
        repository.findByItem(item)
    }
}