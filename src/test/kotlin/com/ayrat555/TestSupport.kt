package com.ayrat555

import com.google.common.io.CharStreams
import com.google.common.io.Resources
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.file.Path
import java.nio.file.Paths

class TestSupport {
    companion object {
        fun getFile(path: String): Path {
            val stringPath = Resources.getResource("files/example.epub").path

            return Paths.get(stringPath)
        }

        fun streamToString(inputStream: InputStream) = inputStream.use {
            val streamReader = InputStreamReader(it, "UTF-8")

            CharStreams.toString(streamReader)
        }
    }
}
