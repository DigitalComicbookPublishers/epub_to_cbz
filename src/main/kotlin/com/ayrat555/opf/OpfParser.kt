package com.ayrat555.opf

import com.ayrat555.domain.Item
import com.ayrat555.domain.Opf
import com.ayrat555.errors.OpfException
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

    private fun findManifest(document: Document) : Node =
            document.documentElement.getElementsByTagName("manifest")
                    .also {
                        if (it.length == 0)
                            throw OpfException("manifest element is not found in opf file")
                    }.item(0)

    private fun findItems(manifest: Node) : List<Item> {
        val nodes = manifest.childNodes

        return (0 until nodes.length - 1)
                .map { idx -> nodes.item(idx) }
                .filter { it.hasAttributes() }
                .map { itemFromNode(it) }
                .also {
                    if (it.isEmpty())
                        throw OpfException("there are no items in opf file")
                }
    }

    private fun itemFromNode(itemNode: Node) : Item =
            Item(
                    href = textContent(itemNode, "href"),
                    id = textContent(itemNode, "id"),
                    mediaType = textContent(itemNode, "media-type")
            )

    private fun textContent(node: Node, name: String) : String =
        node.attributes.getNamedItem(name).textContent
}
