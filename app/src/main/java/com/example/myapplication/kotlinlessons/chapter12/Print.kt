package com.example.myapplication.kotlinlessons.chapter12

interface Print {
    fun firstName(firstName: String): Unit
    fun fullName(firstName: String, lastName: String): Unit
    fun nameAndLocation(name: String, location: String): Unit
}