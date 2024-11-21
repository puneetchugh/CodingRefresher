package com.example.myapplication.kotlinlessons.chapter12

object Chapter12 {

    class Chapter12Class {
        val property: String = "This is a property"
            get() {
                return field.length.toString()
            }
        val field: String = "Puneet Chugh"
            get() = field

        var anotherProperty = ""
            set(value) {
                field = "Prefix: $value"
            }

        fun printValues() {
            println("$TAG: property - $property")
            println("$TAG: field - $field")
            println("$TAG: anotherProperty - $anotherProperty")
        }

    }

    fun getters() {
        val chapter12Class = Chapter12Class()
        chapter12Class.anotherProperty = "Another property"
        chapter12Class.printValues()
    }

    val TAG = Chapter12::class.java.simpleName
    fun print() {

        val PrintImplementation = object : Print {
            override fun firstName(firstName: String) {
                TODO("Not yet implemented")
            }

            override fun fullName(firstName: String, lastName: String) {
                TODO("Not yet implemented")
            }

            override fun nameAndLocation(name: String, location: String) {
                TODO("Not yet implemented")
            }

        }


    }

}
