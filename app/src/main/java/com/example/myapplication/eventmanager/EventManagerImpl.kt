package com.example.kotlinapp.eventmanager

import java.lang.ref.WeakReference

class EventManagerImpl : EventManager {

    // val mapOfEvents: MutableMap<String, MutableList<Pair<Any, () -> Unit>>> = mutableMapOf()
    private val mapOfEvents: MutableMap<String, MutableList<Pair<WeakReference<Any>, () -> Unit>>> =
        mutableMapOf()

    override fun register(eventName: String, observer: Any, closure: () -> Unit) {
        mapOfEvents.getOrPut(eventName) { mutableListOf<Pair<WeakReference<Any>, () -> Unit>>() }
            .add(WeakReference(observer) to closure)
    }

    override fun deregister(observer: Any) {

        val allEvents = mapOfEvents.keys.toList()
        for (element in allEvents) {
            (mapOfEvents.getOrElse(element) { null }?.let { pairList ->
                val pair = pairList.firstOrNull { it == observer }
                pair?.let { pairList.remove(pair) }
                //alternate way to do
                /*
                pairList.firstOrNull { matchingObserver -> matchingObserver == observer }
                    ?.let { pairList.remove(it) }
                 */
            })
            val remainingList = mapOfEvents.getOrElse(element) { null }
            remainingList?.takeIf { it.size == 0 }?.let { mapOfEvents.remove(element) }
        }
    }

    override fun post(eventName: String) {
        mapOfEvents.getOrElse(eventName) { null }
            ?.let { pairs: MutableList<Pair<WeakReference<Any>, () -> Unit>> ->
                pairs.forEach { pair: Pair<Any, () -> Unit> ->
                    pair.second.invoke()
                }
            }
    }
}

class TestObserver(private val name: String) {
    var count = 0
    fun onEvent() {
        count++
        println("Inside $name onEvent() for event: , counter is $count")
    }
}