package com.ayrat555

import com.ayrat555.comicbook.Factory
import com.ayrat555.comicbook.Writer
import com.ayrat555.opf.OpfFetcher
import com.ayrat555.opf.OpfParser
import java.nio.file.Path

class EpubToCbz {
    companion object {
        fun convert(epubPath: Path, outputPath: Path) {
            val opf = fetchOpf(epubPath)
            val comicbook = Factory(opf).create()

            Writer(
                    epubPath = epubPath,
                    outputPath = outputPath,
                    comicbook = comicbook
            ).write()

        }

        private fun fetchOpf(epubPath: Path) =
            OpfFetcher(epubPath).fetchStream().use {
                OpfParser(it).parse()
            }
    }
}
