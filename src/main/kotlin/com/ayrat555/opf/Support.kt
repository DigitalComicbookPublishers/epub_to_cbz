package com.ayrat555.opf

import org.w3c.dom.Node
import org.w3c.dom.NodeList

operator fun NodeList.iterator() : Iterator<Node>  = object : Iterator<Node> {
    var counter = 0

    override fun next(): Node {
        val currentNode =  item(counter)

        counter++

        return currentNode
    }

    override fun hasNext() = counter < length
}

