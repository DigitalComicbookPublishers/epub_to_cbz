package com.ayrat555.epub_to_cbz.comicbook

import com.ayrat555.epub_to_cbz.comicbook.Factory
import com.ayrat555.epub_to_cbz.comicbook.Writer
import com.ayrat555.epub_to_cbz.TestSupport
import com.ayrat555.epub_to_cbz.opf.OpfFetcher
import com.ayrat555.epub_to_cbz.opf.OpfParser
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import java.nio.file.Files

class WriterSpec: Spek({
    describe("write") {
        val comicbookFilePath = TestSupport.getFile("files/example.epub")
        val opfStream = OpfFetcher(comicbookFilePath).fetchStream()
        val opf = OpfParser(opfStream).parse()
        val comicbook = Factory(opf).create()

        it("writes comicbook to file") {
            val tempFile = Files.createTempFile("comicbook", ".cbz")
            val writer = Writer(epubPath = comicbookFilePath, outputPath = tempFile, comicbook = comicbook)

            writer.write()
        }
    }
})

