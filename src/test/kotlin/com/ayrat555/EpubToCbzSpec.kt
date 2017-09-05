package com.ayrat555

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import java.nio.file.Files
import java.nio.file.Paths
import java.util.zip.ZipFile
import kotlin.test.assertEquals

class EpubToCbzSpec: Spek({
    describe("convert"){
        val comicbookFilePath = TestSupport.getFile("files/example.epub")
        val outputPath = Files.createTempFile("comicbook", ".cbz")

        it("converts epub to cbz") {
            EpubToCbz.convert(comicbookFilePath, outputPath)

            val entriesSize = ZipFile(outputPath.toFile()).entries().toList().size
            assertEquals(9, entriesSize)
        }
    }
})
