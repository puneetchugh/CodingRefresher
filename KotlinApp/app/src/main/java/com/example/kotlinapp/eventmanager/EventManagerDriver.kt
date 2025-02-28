package com.example.kotlinapp.eventmanager

object EventManagerDriver {

    fun eventManagerDriver(){
        val observer1 = TestObserver("Observer 1")
        val observer2 = TestObserver("Observer 2")
        val observer3 = TestObserver("Observer 3")
        val observer4 = TestObserver("Observer 4")
        val observer5 = TestObserver("Observer 5")

        //One event and one observer
        val eventManager = EventManagerImpl()
        eventManager.register(eventName = "Training", observer1, observer1::onEvent)
        eventManager.post("Training")
        println("Training event, count: ${observer1.count}")

        //One event and multiple observer
        eventManager.register(eventName = "Awards", observer2, observer2::onEvent)
        eventManager.register(eventName = "Awards", observer3, observer3::onEvent)

        eventManager.post("Awards")
        println("Awards event, observer4, count: ${observer2.count}")
        println("Awards event, observer5, count: ${observer3.count}")

        //Multiple events and one observer
        eventManager.register(eventName = "Meeting", observer4, observer4::onEvent)
        eventManager.register(eventName = "Party", observer4, observer4::onEvent)

        eventManager.post("Meeting")
        eventManager.post("Party")
        println("Meeting and Party events, observer4, count: ${observer4.count}")
    }
}