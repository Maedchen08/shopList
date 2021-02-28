package com.example.shopping.data.repository

import com.example.shopping.data.model.Item

interface ItemRepositoryInterface {
    fun list(): List<Item>
    fun save(item: Item): Item
    fun delete(item: Item): Item
    fun findByItem(item:Item) :Item


}