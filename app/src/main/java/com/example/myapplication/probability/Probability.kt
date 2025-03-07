package com.example.myapplication.probability

object Probability {

    enum class RestaurantType {
        indian,
        chinese,
        mexican,
        american,
        burmesse,
        peruvian
    }

    fun driverFunction() {

        val mapOfRestaurantToVotes = mapOf(
            RestaurantType.indian to 4,
            RestaurantType.chinese to 2,
            RestaurantType.mexican to 3,
            RestaurantType.american to 1,
            RestaurantType.burmesse to 3,
            RestaurantType.peruvian to 1
        )

        println("Probability: Inside driverFunction, Restaurant to Votes: $mapOfRestaurantToVotes")

        for (counter in 0 until 10) {
            println("Probability: Iteration $counter, restaurant picked: ${pickARestaurant(mapOfRestaurantToVotes)}")
        }

    }

    //it will get total no of votes for each of the restaurant as input,
    //pickARestaurant() picks a restaurant with higher votes having higher probability
    fun pickARestaurant(map: Map<RestaurantType, Int>): RestaurantType {

        val totalVotes = map.values.reduce { input1, input2 -> input1 + input2 }
        var movingCount = 0
        val rangeToRestaurant =
            map.entries.associate { entry ->
                val range = movingCount..<movingCount + entry.value
                movingCount = range.last+1
                println("Probability: Pair Range to Restaurant: ${range to entry.key}")
                range to entry.key
            }
        println("Probability: range to RestaurantType: $rangeToRestaurant")
        val randomNumber = (0..<totalVotes).random()
        println("Probability: random number is $randomNumber")
        val retVal = rangeToRestaurant.entries.find { entry -> randomNumber in entry.key }!!.value
        return retVal
    }
}