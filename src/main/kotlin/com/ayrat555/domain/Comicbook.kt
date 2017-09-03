package com.ayrat555.domain

data class Image(val pathInZip: String)
data class Comicbook(val images: List<Image>)
