package com.ayrat555.epub_to_cbz

import com.ayrat555.epub_to_cbz.comicbook.Factory
import com.ayrat555.epub_to_cbz.comicbook.Writer
import com.ayrat555.epub_to_cbz.opf.OpfFetcher
import com.ayrat555.epub_to_cbz.opf.OpfParser
import java.nio.file.Path

class EpubToCbz {
    companion object {
        @JvmStatic
        fun convert(epubPath: Path, outputPath: Path) {
            val opf = fetchOpf(epubPath)
            val comicbook = Factory(opf).create()

            Writer(
                    epubPath = epubPath,
                    outputPath = outputPath,
                    comicbook = comicbook
            ).write()

        }

        @JvmStatic
        private fun fetchOpf(epubPath: Path) =
            OpfFetcher(epubPath).fetchStream().use {
                OpfParser(it).parse()
            }
    }
}
