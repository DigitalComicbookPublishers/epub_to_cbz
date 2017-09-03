package com.ayrat555.comicbook

import com.ayrat555.TestSupport
import com.ayrat555.opf.OpfFetcher
import com.ayrat555.opf.OpfParser
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class BuilderSpec: Spek({
    describe("build"){
        val comicbookFilePath = TestSupport.getFile("files/opf_example.epub")
        val opfStream = OpfFetcher(comicbookFilePath).fetchStream()
        val opf = OpfParser(opfStream).parse()

        it("builds comicbook") {
            val comicbook = Builder(comicbookFilePath, opf).build()

            assertEquals(9, comicbook.images.size)
        }
    }
})
