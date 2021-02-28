package com.example.shopping.data.repository

import com.example.shopping.data.model.Item
import java.util.*

class ItemRepository : ItemRepositoryInterface {
    companion object {
        var itemList = arrayListOf(
            Item(
                UUID.randomUUID().toString(),
                "1/1/2020",
                "123",
                123,
                "note"
            ),
            Item(
                UUID.randomUUID().toString(),
                "2/1/2020",
                "123",
                123,
                "note"
            ),
            Item(
                UUID.randomUUID().toString(),
                "1/3/2020",
                "123",
                123,
                "note"
            )
        )
    }

    override fun list(): List<Item> = itemList
    override fun save(data: Item): Item {
        if (data.id == "") {
            data.id = UUID.randomUUID().toString()
            itemList.add(data)
        } else {
            val item = itemList.filter{
                it.id == data.id
            }
            val index = itemList.indexOf(item.single())
            itemList[index] = data
        }
        return data
    }

    override fun delete(item: Item): Item {
        val index = itemList.indexOf(item)
        itemList.removeAt(index)
        return item

    }

    override fun findByItem(item: Item): Item = itemList?.get(itemList.indexOf(item))

}