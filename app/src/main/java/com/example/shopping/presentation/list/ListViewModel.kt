package com.example.shopping.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopping.data.model.Item
import com.example.shopping.data.repository.ItemRepositoryInterface

class ListViewModel(private val repository: ItemRepositoryInterface) : ViewModel() {

    private var _itemLiveData = MutableLiveData<List<Item>>()

    val itemsLiveData : LiveData<List<Item>>
    get() {
        loadItemData()
        return _itemLiveData
    }


    private fun loadItemData() {
        _itemLiveData.value = repository.list()
    }
}