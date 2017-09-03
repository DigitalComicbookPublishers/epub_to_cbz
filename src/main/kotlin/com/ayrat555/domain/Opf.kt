package com.ayrat555.domain

data class Item(val href: String, val id: String, val mediaType: String)
data class Opf(val items: List<Item>)
