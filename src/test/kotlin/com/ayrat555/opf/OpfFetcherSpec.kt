package com.ayrat555.opf

import com.ayrat555.TestSupport
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertTrue

class OpfFetcherSpec : Spek({
    describe("parse") {
        val comicbookFilePath = TestSupport.getFile("files/example.epub")

        it("fethes opf file from epub document") {
            val opfFetcher = OpfFetcher(comicbookFilePath)

            val opfString = TestSupport.streamToString(opfFetcher.fetchStream())

            assertTrue(opfString.contains("<metadata xmlns:dc=\"http://purl.org/dc/elements/1.1/\" xmlns:opf=\"http://www.idpf.org/2007/opf\">"))
        }
    }
})
