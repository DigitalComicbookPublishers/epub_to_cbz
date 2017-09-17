package com.ayrat555.epub_to_cbz.comicbook

import com.ayrat555.epub_to_cbz.domain.Comicbook
import com.ayrat555.epub_to_cbz.domain.Image
import com.ayrat555.epub_to_cbz.domain.Opf
import com.ayrat555.epub_to_cbz.errors.ComicbookException

class Factory(val opf: Opf) {
    fun create() : Comicbook {
        val images = findImages()

        return Comicbook(images = images)
    }

    private fun findImages() : List<Image> =
            opf.items.filter {
                it.mediaType.contains("image")
            }.also {
                if (it.isEmpty())
                    throw ComicbookException("there are no images in manifest")
            }.map { Image(pathInZip = it.href) }
}
