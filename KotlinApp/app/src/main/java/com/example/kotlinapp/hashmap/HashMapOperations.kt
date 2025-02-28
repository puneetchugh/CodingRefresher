package com.example.kotlinapp.hashmap

object HashMapOperations {

    private val mapOfNames: MutableMap<Char, MutableList<String>>

    init {
        mapOfNames = mutableMapOf()
    }

    fun driverFunction() {
        val listOfNames = listOf(
            "Puneet",
            "Chris",
            "Nishu",
            "Shweta",
            "Nidhi",
            "Seema",
            "Haider",
            "Lily",
            "April",
            "John"
        )
        for (name in listOfNames) {
            addName(name)
        }
        deleteName("Chris")
        deleteName("Nishu")

        println("associate, createMapFromList(), map: ${createMapFromList(listOfNames)}")
        println("associateBy, createMapFromList1(), map: ${createMapFromList1(listOfNames)}")
        println("associateTo, addToExistingMap1(), map: ${addToExistingMap1(listOfNames)}")
        println("associateByTo, addToExistingMap2(), map: ${addToExistingMap2(listOfNames)}")
        println("associateByTo, addToExistingMap3(), map: ${addToExistingMap3(listOfNames)}")

    }

    fun addName(name: String) {
        name.isNotEmpty().takeIf { it }?.let {
            mapOfNames.getOrPut(name.first()) { mutableListOf() }.add(name)
        }
        println("map: $mapOfNames")
    }

    fun deleteName(name: String) {
        println("map: ${mapOfNames}")
        /*val list = mapOfNames[name.first()]
        list?.remove(name)?.takeIf { it }?.let {
            if (list.size == 0) {
                mapOfNames.remove(name.first())
            }
        }*/
        mapOfNames[name.first()]?.remove(name)?.takeIf { it }?.let {
            println("removed name: $name")
            mapOfNames[name.first()]?.takeIf { it.size == 0 }?.let {
                println("removing key-value pair with key, ${name.first()}")
                mapOfNames.remove(name.first())
            }
        }
    }

    //only one to one mapping, First Character to last Name encountered
    fun createMapFromList(namesList: List<String>): Map<Char, String> {
        return namesList.associate { it.first() to it }
    }

    fun createMapFromList1(namesList: List<String>): Map<Char, String> {
        return namesList.associateBy({ it.first() }, { it })
    }

    fun addToExistingMap1(namesList: List<String>): Map<Char, String> {
        val existingMap = mutableMapOf<Char, String>()
        return namesList.associateTo(existingMap) { it.first() to it }
    }

    fun addToExistingMap2(namesList: List<String>): Map<Char, String> {
        val existingMap = mutableMapOf<Char, String>()
        return namesList.associateByTo(existingMap, { it.first() }, { it })
    }

    fun addToExistingMap3(namesList: List<String>): Map<Char, String> {
        val existingMap = mutableMapOf<Char, String>()
        return namesList.associateByTo(existingMap) { it.first() }
    }


}