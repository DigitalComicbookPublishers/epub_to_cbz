package com.ayrat555.opf

import java.io.InputStream
import java.nio.file.Path
import java.util.zip.ZipEntry
import java.util.zip.ZipFile

class OpfNotFound(message: String, cause: Exception? = null) : RuntimeException(message, cause)

class OpfFetcher(pathToEpub: Path) {
    private val zip = ZipFile(pathToEpub.toString())

    companion object {
        val OPF_EXTENSION = ".opf"
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

        throw OpfNotFound("Opf file is not found in epub")
    }
}
