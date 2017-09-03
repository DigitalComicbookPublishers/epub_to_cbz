package com.ayrat555.comicbook

import com.ayrat555.domain.Comicbook
import java.io.FileOutputStream
import java.nio.file.Path
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream

class Writer(val epubPath: Path, val outputPath: Path, val comicbook: Comicbook) {
    fun write() {
        val epub = ZipFile(epubPath.toFile())

        FileOutputStream(outputPath.toFile()).use { fileOutputStream ->
            ZipOutputStream(fileOutputStream).use { zipOutputStream ->
                comicbook.images
                        .map { epub.getEntry(it.pathInZip) }
                        .forEach { zipOutputStream.putNextEntry(it) }
            }
        }

    }
}
