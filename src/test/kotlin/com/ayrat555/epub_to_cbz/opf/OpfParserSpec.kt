package com.ayrat555.epub_to_cbz.opf

import com.ayrat555.epub_to_cbz.TestSupport
import com.ayrat555.epub_to_cbz.opf.OpfFetcher
import com.ayrat555.epub_to_cbz.opf.OpfParser
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class OpfParserSpec : Spek({
    describe("parse"){
        val comicbookFilePath = TestSupport.getFile("files/example.epub")
        val opfStream = OpfFetcher(comicbookFilePath).fetchStream()

        it("parses opf") {
            val opf = OpfParser(opfStream).parse()

            assertEquals(19, opf.items.size)
        }
    }
})
