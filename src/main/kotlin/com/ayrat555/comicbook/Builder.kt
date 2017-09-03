package com.ayrat555.comicbook

import apple.laf.JRSUIUtils
import com.ayrat555.domain.Comicbook
import com.ayrat555.domain.Image
import com.ayrat555.domain.Opf
import java.nio.file.Path

class Builder(val zipPath: Path, val opf: Opf) {
    fun build() : Comicbook {
        val images = findImages()

        return Comicbook(images = images)
    }

    fun findImages() : List<Image> {
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
