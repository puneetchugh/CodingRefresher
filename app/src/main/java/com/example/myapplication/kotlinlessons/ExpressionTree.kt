package com.example.myapplication.kotlinlessons

import android.util.Log
import java.util.Stack

data class Node(val data: String, var left: Node? = null, var right: Node? = null)

object ExpressionTree {

    fun calculateExpression(input: String) {
        Log.e("PuneetChugh", "Inside calculateExpression(), input: $input")
        val root = convertToBinaryTree(input)
        inOrder(root)
    }

    private fun convertToBinaryTree(input: String): Node {

        val charStack = Stack<Char>()
        val nodeStack = Stack<Node>()
        var counter = 0
        while (counter < input.length) {
            val current = input[counter]
            Log.e("PuneetChugh", "Inside outer while loop, current: $current")

            if (current == '(') {
                charStack.push(current)
            } else if (current.isDigit()) {
                val stringBuilder = StringBuilder()
                while (counter < input.length && input[counter].isDigit()) {
                    stringBuilder.append(input[counter])
                    counter++
                }
                nodeStack.push(Node(data = stringBuilder.toString()))
                counter--
            } else if (current in "+-*/^") {
                while (charStack.isNotEmpty() && charStack.peek() in "+-*/^" && operatorPrecedence(
                        current
                    ) <= operatorPrecedence(charStack.peek())
                ) {
                    val node = Node(data = charStack.pop().toString())

                    node.right = nodeStack.pop()
                    node.left = nodeStack.pop()
                    nodeStack.push(node)
                }
                charStack.push(current)
            } else if (current == ')') {
                Log.e("PuneetChugh", "Encountered, $current")
                while (charStack.isNotEmpty() && charStack.peek() != '(') {
                    val charNode = Node(data = charStack.pop().toString())

                    charNode.right = nodeStack.pop()
                    charNode.left = nodeStack.pop()
                    println("Popping char out of charStack: ${charNode.data}, left: ${charNode.left?.data}, right: ${charNode.right?.data}")
                    nodeStack.push(charNode)
                }
                charStack.pop()
            }
            counter++
        }

        while (charStack.isNotEmpty()) {
            val charNode = Node(charStack.pop().toString())
            charNode.right = nodeStack.pop()
            charNode.left = nodeStack.pop()
            nodeStack.push(charNode)
        }
        println("size of stack: ${nodeStack.size}")
        return nodeStack.pop()
    }

    private fun operatorPrecedence(operator: Char): Int {

        return when (operator) {
            '+', '-' -> 1
            '*', '/' -> 2
            '^' -> 3
            ')' -> 0
            else -> -1
        }
    }

    fun preOrder(node: Node?) {
        if (node == null)
            return
        println(node.data)
        inOrder(node.left)
        inOrder(node.right)
    }

    private fun inOrder(node: Node?) {
        if (node == null)
            return
        inOrder(node.left)
        println(node.data)
        inOrder(node.right)
    }
}