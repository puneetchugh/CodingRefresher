package com.example.myapplication.kotlinlessons

object LessonEight {

    val TAG = LessonEight::class.simpleName
    fun createArrays() {

        val array1 = Array(10) { 10 }
        println("$TAG: array1 - ${array1.joinToString("||")}")

        val array2 = Array(5) { i -> i * 3 }
        println(
            "$TAG: array2: ${
                array2.joinToString(
                    separator = "|",
                    prefix = "Integer: [",
                    postfix = "]",
                    transform = { input -> "{$input}" })
            }"
        )

        val array3 = arrayOf("20", 10, null, "")
        println("$TAG: array3 - ${array3.joinToString()}")

        val array4 = "aeiou".toCharArray(startIndex = 0, endIndex = 3)
        println("$TAG: array4 - ${array4.joinToString()}")

        val intArray1 = IntArray(4) { i -> i * 11 }
        println("$TAG: intArray1 - ${intArray1.joinToString()}")

        val intArray2 = array1.toIntArray()
        println("$TAG: intArray2 - ${intArray2.joinToString(" | ")}")

        val intArray3 = intArrayOf(10.2.toInt())
        println("$TAG: intArray3 - ${intArray3.joinToString(" | ")}")

        val intList1 = mutableListOf(1..5)
        println("$TAG: intList1 - intList1 size: ${intList1.size} ${intList1.map { element -> element.map { it } }}}")

        val emptyList = emptyList<Int>()
        val emptyList1 = listOf<Int>()
        val minOrNull = intArray2.minOrNull()
        println("$TAG: min - ${minOrNull ?: Int.MIN_VALUE}")
        //        println("$TAG: when list is empty, min - ${emptyList.min}") - throws Runtime error
        println("$TAG: when list is empty, min - ${emptyList.minOrNull()}")

        val listOfInts = mutableListOf(11, 22, 33, 44)
        println("$TAG: list[2]: ${listOfInts[2]}")
        println("$TAG: listOfInts contains 22: ${22 in listOfInts}")
        println("$TAG: reversed: ${listOfInts.reversed()}, original: $listOfInts")
        println("$TAG: reverse: ${listOfInts.reverse()}, original: $listOfInts")

        val listOfNames = listOf("Puneet Chugh", "Chris Gray", "Mike Singh")
        listOfNames.forEachIndexed { index, name ->
            println("$TAG: forEachIndexed, index - $index, name - $name")
        }
        for ((index, name) in listOfNames.withIndex()) {
            println("$TAG: for loop, index - $index, name - $name")
        }

        val mike = "MIKE"
        val mutableListOfNames = mutableListOf("Mike Tyson", "Jake Paul", "Logan Paul")
        println("$TAG: element 0 before changed: ${mutableListOfNames[0]}")
        mutableListOfNames[0] = mike
        println("$TAG: element 0 after changed: ${mutableListOfNames[0]}")
        println("$TAG: position of $mike: ${mutableListOfNames.indexOf(mike)}")
        println("$TAG: position of mike: ${mutableListOfNames.indexOf("mike")}")
    }

    fun challenges() {
        removeFirstOccurrence()
        removeAllOccurrence()
    }

    private fun removeFirstOccurrence() {
        val list = mutableListOf(11, 15, 1, 11, 90, 99, 11)
        println("$TAG: array before removal: ${list.joinToString()}")
        println("$TAG: Challenge - index of 11: ${list.indexOf(11)}")
        val removed = list.remove(11)
        println("$TAG: removed element: $removed, array after removal - ${list.joinToString() }} ")
    }

    private fun removeAllOccurrence() {
        //val array = mutableListOf()
        println("$TAG: ---------- Remove all -----------")
        val mutableList = mutableListOf(11, 15, 1, 11, 90, 99, 11)
        println("$TAG: array before removal: ${mutableList.joinToString()}")

        println("$TAG: array after removal: ${mutableList.filter { it != 11 }}")

        mutableList.removeAll{it==11}
        println("$TAG: array after removal (in-place): ${mutableList.joinToString()}")

    }


}