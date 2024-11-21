package com.example.myapplication.kotlinlessons

import kotlin.random.Random

object ChapterTen {

    enum class Operators(val operatorString: String) {
        SUM("sum"),
        SUBTRACT("subtract"),
        MULTIPLY("multiple"),
        DIVIDE("divide");

        companion object {
            infix fun from(operatorString: String): Operators? {
                //return valueOf(operatorString) - works as well
                return values().firstOrNull { it.operatorString == operatorString }
            }
        }
    }

    private val TAG = ChapterTen::class.simpleName

    fun operation(input1: Int, input2: Int, operatorString: String): Int {
        return when (Operators.from(operatorString)) {
            Operators.SUM -> input1.plus(input2)
            Operators.SUBTRACT -> input1.minus(input2)
            Operators.MULTIPLY -> input2 * input1
            Operators.DIVIDE -> input1.div(input2)
            null -> input1.plus(input2)
        }
    }

    private fun operateOnNumbers(
        input1: Int,
        input2: Int,
        operatorString: String,
        operation: (Int, Int, String) -> Int
    ): Int {
        return operation(input1, input2, operatorString)
    }

    fun creatingLambdas() {

        val num1 = 10
        val num2 = 20

        val customLambda: () -> Unit = { println("$TAG: Just print") }
        val customLambda1: (input: Int) -> Int = { input ->
            input + Random.nextInt()
        }

        val customLambda2: (input1: Int, input2: Int) -> Int = { input1: Int, input2: Int ->
            input1 * input2
        }
        println("$TAG: customLambda2 = ${customLambda2(num1, num2)}")

        val customLambda3: (input1: Int, input2: Int) -> Int = Int::plus

        val copyingAnotherLambda: () -> Unit = customLambda

        println(
            "$TAG: Adding $num1 and $num2 = ${
                operateOnNumbers(
                    input1 = num1,
                    input2 = num2,
                    operatorString = "sum",
                    operation = ::operation
                )
            }"
        )
        println(
            "$TAG: Subtracting $num1 and $num2 = ${
                operateOnNumbers(
                    input1 = num1,
                    input2 = num2,
                    operatorString = "subtract",
                    operation = ::operation
                )
            }"
        )
        println(
            "$TAG: Multiple $num1 and $num2 = ${
                operateOnNumbers(
                    input1 = num1,
                    input2 = num2,
                    operatorString = "multiple",
                    operation = ::operation
                )
            }"
        )
        println(
            "$TAG: Dividing $num1 and $num2 = ${
                operateOnNumbers(
                    input1 = num1,
                    input2 = num2,
                    operatorString = "divide",
                    operation = ::operation
                )
            }"
        )
    }

    fun builtInMapAssociateLambdas() {
        val arrayOfNumbers = arrayOf(10, 11, 21, 31, 50, 75)

        val map1 = arrayOfNumbers.associate { it -> Pair(it, it * it) }
        println("$TAG: mapAssociate ${map1.entries.joinToString(",,")}")
        map1.entries.joinToString(",,")

        // associateBy -> the key is transformed
        val mapAssociateBy = arrayOfNumbers.associateBy { element -> element * 2 }
        println("$TAG: mapAssociateBy ${mapAssociateBy.entries.joinToString(",,")}")

        // associateWith -> the value is transformed
        val mapAssociateWith = arrayOfNumbers.associateWith { element -> element * 2 }
        println("$TAG: mapAssociateWith ${mapAssociateWith.entries.joinToString(",,")}")
    }

    fun builtInLambdas() {
        val array = arrayOf(10, 20, 30, 40, 50, 60, 70, 80, 90)

        // reduce
        val usingReduce = array.reduceOrNull() { input1, input2 -> input1 + input2 }
        println("$TAG: after reduce operation, $usingReduce")

        // fold has an initial value
        val usingFold = array.fold(initial = 10) { initial, listElement -> initial + listElement }
        println("$TAG: after fold operation with 10 as initial value, $usingFold")
    }

    fun challenges() {
        val appRatings = mapOf(
            "Calendar Pro" to arrayOf(1, 5, 5, 4, 2, 1, 5, 4),
            "The Messenger" to arrayOf(5, 4, 2, 5, 4, 1, 1, 2),
            "Socialise" to arrayOf(2, 1, 2, 2, 1, 2, 4, 2)
        )

        val avgAppRating: MutableMap<String, Float> = mutableMapOf()

        appRatings.forEach { (key, value) ->
            avgAppRating[key] =
                value.reduce { input1, input2 -> input1 + input2 }.div(value.size.toFloat())
        }
        avgAppRating.forEach { (key, value) ->
            println("$TAG: Avg app rating for $key is $value")
        }

        println(
            "$TAG: Apps with rating more than 3:0 = ${
                avgAppRating.filter { it.value >= 3.0 }.map { it.key }.joinToString(separator = ",,")
            }"
        )

    }
}