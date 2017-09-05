package com.ayrat555.comicbook

import com.ayrat555.domain.Comicbook
import com.ayrat555.domain.Image
import com.ayrat555.domain.Opf
import com.ayrat555.errors.ComicbookException

class Factory(val opf: Opf) {
    fun create() : Comicbook {
        val images = findImages()

        return Comicbook(images = images)
    }

    private fun findImages() : List<Image> {
        val imageItems = opf.items.filter {
            it.mediaType.contains("image")
        }

        if (imageItems.isEmpty())
            throw ComicbookException("there are no images in manifest")

        return imageItems.map {
            Image(pathInZip = it.href)
        }
    }
}
