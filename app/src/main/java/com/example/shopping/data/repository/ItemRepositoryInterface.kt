package com.example.shopping.data.repository

import com.example.shopping.data.model.Item

interface ItemRepositoryInterface  {
fun list():List<Item>

}