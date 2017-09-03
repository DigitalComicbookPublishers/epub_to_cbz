package com.ayrat555.comicbook

import com.ayrat555.TestSupport
import com.ayrat555.opf.OpfFetcher
import com.ayrat555.opf.OpfParser
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import java.nio.file.Files

class WriterSpec: Spek({
    describe("write") {
        val comicbookFilePath = TestSupport.getFile("files/example.epub")
        val opfStream = OpfFetcher(comicbookFilePath).fetchStream()
        val opf = OpfParser(opfStream).parse()
        val comicbook = Builder(opf).build()

        it("writes comicbook to file") {
            val tempFile = Files.createTempFile("comicbook", ".cbz")
            val writer = Writer(epubPath = comicbookFilePath, outputPath = tempFile, comicbook = comicbook)

            writer.write()
        }
    }
})

