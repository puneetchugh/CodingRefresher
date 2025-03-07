package com.example.myapplication.priorityqueues

import java.util.PriorityQueue

object TopMost {

    fun driverFunction() {

        val string =
            "This is a string. String is staring at you, what else can a string do? It can stop staring at you."

        val topMost = getTopMostUsed(input = string, topMostCount = 5)
        println("TopMost words: ${topMost.joinToString(",")}")
    }

    fun getTopMostUsed(input: String, topMostCount: Int): List<String> {

        val mapOfWordsToCount = mutableMapOf<String, Int>()

        val arrayOfWords = input.lowercase().split(".", ",", " ", "\"", ":", ";", "?")
        println("All the words: ${arrayOfWords.joinToString(",")}")
        arrayOfWords.forEach { word ->
            if (word.trim().isNotEmpty()) {
                mapOfWordsToCount.getOrPut(word) { 0 }.plus(1).also {
                    mapOfWordsToCount[word] = it
                }
            }
        }
        println("All the words: $mapOfWordsToCount")
        val priorityQueue =
            PriorityQueue<Pair<String, Int>>(5) { input1, input2 -> input2.second - input1.second }

        mapOfWordsToCount.forEach { entry ->
            priorityQueue.add(Pair(entry.key, entry.value))
        }

        val mutableList = mutableListOf<String>()

        var counter = 0
        while (priorityQueue.isNotEmpty() && counter < topMostCount) {
            mutableList.add(priorityQueue.remove().first)
            counter++
        }

        println("TopMost words: ${priorityQueue.toList().subList(0, 5).joinToString(",")}")
        return mutableList
    }
}