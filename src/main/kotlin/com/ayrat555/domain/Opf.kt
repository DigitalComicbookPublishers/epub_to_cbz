package com.ayrat555.domain

class Item(val href: String, val id: String, val mediaType: String)
class Opf(val items: List<Item>)
