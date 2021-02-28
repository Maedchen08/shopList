package com.example.shopping.presentation.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopping.data.model.Item
import com.example.shopping.data.repository.ItemRepository
import com.example.shopping.data.repository.ItemRepositoryInterface
import com.example.shopping.utils.ResourceState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FormViewModel(val repository: ItemRepository) : ViewModel() {

    private var _itemLiveData = MutableLiveData<Item>()
    private var _isValid = MutableLiveData<ResourceState>()

    val itemLiveData : LiveData<Item>
        get() {
            return _itemLiveData
        }

    val isValid: LiveData<ResourceState>
        get() {
            return _isValid
        }

    fun save(item: Item) {
        _itemLiveData.value = repository.save(item)
    }

    fun validation(item: Item) {
        GlobalScope.launch {
            _isValid.postValue(ResourceState.loading())
            delay(2000)
            if (item.date.isNullOrBlank()) {
                _isValid.postValue(ResourceState.fail("Date can not empty"))
            } else if (item.name.isNullOrBlank()) {
                _isValid.postValue(ResourceState.fail("Name can not empty"))
            } else if (item.note.isNullOrBlank()) {
                _isValid.postValue(ResourceState.fail("Note can not empty"))
            } else if (item.quantity.toString().isNullOrBlank()) {
                _isValid.postValue(ResourceState.fail("Quantity can not empty"))
            } else {
                _isValid.postValue(ResourceState.success(true))
            }
        }
    }
}