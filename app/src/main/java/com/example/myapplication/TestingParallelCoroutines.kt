package com.example.myapplication

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

object TestingParallelCoroutines {

    fun driverFunction() {
        val coroutineScope =
            CoroutineScope(context = SupervisorJob() + Dispatchers.IO + CoroutineName("TestingCoroutine"))

        coroutineScope.launch {
            println("CoroutineScope: ${currentCoroutineContext()}")
            flowOfInts(coroutineContext = coroutineScope.coroutineContext).collectLatest { listOfPairs ->
                println("Inside TestingParallelCoroutines->driverFunction(), $listOfPairs")
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun flowOfInts(coroutineContext: CoroutineContext): Flow<List<Pair<Int, Int>>> = flow {
        println("Coroutines: Inside flowOfInts()..flow{}")
        val listOfInts = intArrayOf(1, 2, 3, 4, 5, 6)
        //val listOfDeferredReturnValues = mutableListOf<Deferred<Pair<Int, Int>>>()
        val listOfDeferredReturnValues = mutableListOf<Pair<Int, Int>>()

        listOfInts.forEach { input ->

            //val calculate = scope.async { //for parallel execution
            val calculate = withContext(coroutineContext) {
                val delayTime = (0..5000).random()
                delay(delayTime.toLong())
                println("Coroutines: Inside async{}, Pair($input, $delayTime)")
                Pair(input, delayTime)
            }
            println("Coroutines: adding pair to listOfDeferred objects, input: $input, $calculate")
            listOfDeferredReturnValues.add(calculate)
        }

        emit(listOfDeferredReturnValues.map {
            it
        })

        /*
                emit(
                    awaitAll(
                        listOfDeferredReturnValues[0],
                        listOfDeferredReturnValues[1],
                        listOfDeferredReturnValues[2],
                        listOfDeferredReturnValues[3],
                        listOfDeferredReturnValues[4],
                        listOfDeferredReturnValues[5]
                    )
                )*/

    }
}