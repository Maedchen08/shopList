package com.example.shopping.presentation.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping.clickListeners.ItemClickListener
import com.example.shopping.data.model.Item
import com.example.shopping.databinding.CardViewItemBinding

class ListViewHolder(view: View, val itemClickListener: ItemClickListener): RecyclerView.ViewHolder(view) {

    private val binding = CardViewItemBinding.bind(view)

    fun bind(item : Item) {
        binding.apply {
            nameTv.text = item.name
            quantityTv.text = item.quantity.toString()
            dateTv.text = item.date
            noteTv.text = item.note
            deleteItemButton.setOnClickListener {
                itemClickListener.onDelete(item)
            }
        }
    }
}