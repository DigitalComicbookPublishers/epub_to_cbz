package com.ayrat555.epub_to_cbz.opf

import com.ayrat555.epub_to_cbz.errors.OpfException
import java.io.InputStream
import java.nio.file.Path
import java.util.zip.ZipEntry
import java.util.zip.ZipFile

class OpfFetcher(pathToEpub: Path) {
    private val zip = ZipFile(pathToEpub.toString())

    companion object {
        const val OPF_EXTENSION = ".opf"
    }

    fun fetchStream() : InputStream {
        val opfEntry = findOpfEntry(zip)

        return zip.getInputStream(opfEntry)
    }

    private fun findOpfEntry(zipFile: ZipFile) : ZipEntry {
        for (entry in zipFile.entries()) {
            if (entry.name.endsWith(OPF_EXTENSION))
                return entry
        }

        throw OpfException("Opf file is not found in epub")
    }
}
