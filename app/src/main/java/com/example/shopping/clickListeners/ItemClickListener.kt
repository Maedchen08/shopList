package com.example.shopping.clickListeners

import com.example.shopping.data.model.Item

interface ItemClickListener {
 fun onDelete(item:Item)
 fun onEdit(item: Item)
}