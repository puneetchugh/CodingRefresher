package com.example.kotlinapp.eventmanager

interface EventManager {
    fun register(eventName: String, observer: Any, closure: ()->Unit)
    fun deregister(observer: Any)
    fun post(eventName: String)
}