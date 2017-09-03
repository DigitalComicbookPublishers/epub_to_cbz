package com.ayrat555.opf

import com.ayrat555.domain.Item
import com.ayrat555.domain.Opf
import org.w3c.dom.Document
import org.w3c.dom.Node
import java.io.InputStream
import javax.xml.parsers.DocumentBuilderFactory

class OpfParser(val opfStream: InputStream){
    fun parse() : Opf {
        val document = generateDomDocument()
        val manifest = findManifest(document)
        val items = findItems(manifest)

        return Opf(items = items)
    }

    private fun generateDomDocument() : Document {
        val builder = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()

        return opfStream.use {
            val doc = builder.parse(it)
            doc.normalize()

            doc
        }
    }

    private fun findManifest(document: Document) : Node {
        val manifestElements = document.documentElement.getElementsByTagName("manifest")

        if (manifestElements.length == 0)
            throw OpfError("manifest element is not found in opf file")

        return manifestElements.item(0)
    }

    private fun findItems(manifest: Node) : List<Item> {
        val items: MutableList<Item> = ArrayList()

        for (item in manifest.childNodes) {
            if (item.hasAttributes()) {
                val newItem = itemFromNode(item)

                items.add(newItem)
            }
        }

        if (items.isEmpty())
            throw OpfError("there is no items in opf file")

        return items.toList()
    }

    private fun itemFromNode(itemNode: Node) : Item {
        val attributes = itemNode.attributes

        return Item(
              href = attributes.getNamedItem("href").toString(),
                id = attributes.getNamedItem("id").toString(),
                mediaType = attributes.getNamedItem("media-type").toString()
        )
    }
}
