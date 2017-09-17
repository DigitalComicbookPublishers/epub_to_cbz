package com.ayrat555.epub_to_cbz.comicbook

import com.ayrat555.epub_to_cbz.domain.Comicbook
import java.io.FileOutputStream
import java.nio.file.Path
import java.nio.file.Paths
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream

class Writer(val epubPath: Path, val outputPath: Path, val comicbook: Comicbook) {
    private val epubZip = ZipFile(epubPath.toFile())

    fun write() {
        FileOutputStream(outputPath.toFile()).use { fileOutputStream ->
            ZipOutputStream(fileOutputStream).use { zipOutputStream ->
                comicbook.images
                        .map {
                            epubZip.entries().toList().first { e -> e.name.contains(it.pathInZip) }
                        }.forEach { copyEntry(zipOutputStream, it) }
            }
        }
    }

    private fun copyEntry(zipOutputStream: ZipOutputStream, zipEntry: ZipEntry) {
        val fileName = Paths.get(zipEntry.name).fileName.toString()
        val newEntry = ZipEntry(fileName)

        epubZip.getInputStream(zipEntry).use {
            zipOutputStream.putNextEntry(newEntry)
            zipOutputStream.write(it.readBytes())
            zipOutputStream.closeEntry()
        }
    }
}
