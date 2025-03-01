package com.example.myapplication

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object SmallestDistance {

    data class Point(val x: Int, val y: Int)
    data class PointWithDistance(val x: Int, val y: Int, val dist: Int)


    fun driverFunction() {
        val matrix = Array<CharArray>(5) { charArrayOf() }
        val matrix1 = arrayOf<CharArray>(
            charArrayOf('0', '1', '1', 'X', '0'),
            charArrayOf('0', '1', '0', 'X', '0'),
            charArrayOf('1', '1', '1', '1', '0'),
            charArrayOf('0', '0', '1', 'X', '0'),
            charArrayOf('1', '1', '1', '1', '0')
        )
        val shortestDistance =
            findShortestPath(input = matrix1, start = Point(0, 1), target = Point(4, 3))
        println("Print shortest distance: $shortestDistance")
    }

    fun findShortestPath(input: Array<CharArray>, start: Point, target: Point): Int? {
        println("Start: (${start.x}, ${start.y}), Target: (${target.x}, ${target.y})")
        val queue = ArrayDeque<PointWithDistance>()
        queue.add(PointWithDistance(x = start.x, y = start.y, dist = 0))
        val visited: MutableSet<Point> = mutableSetOf<Point>()

        val X = arrayOf(1, 0, 0, -1)
        val Y = arrayOf(0, -1, 1, 0)

        val X_8 = arrayOf(1, 1, 1, 0, 0, -1, -1, -1)
        val Y_8 = arrayOf(-1, 0, 1, -1, 1, -1, 0, 1)
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            visited.add(Point(current.x, current.y))
            println("Visiting: (${current.x},${current.y}, dist: ${current.dist})")
            if (current.x == target.x && current.y == target.y) {
                return current.dist
            }
            for (counter in 0 until X.size) {
                if (isValidMove(
                        x = current.x + X[counter],
                        y = current.y + Y[counter],
                        matrix = input,
                        visited = visited
                    )
                ) {
                    queue.addLast(
                        PointWithDistance(
                            x = current.x + X[counter],
                            y = current.y + Y[counter],
                            dist = current.dist + 1
                        )
                    )
                }
            }
        }
        return null
    }

    private fun isValidMove(
        x: Int,
        y: Int,
        matrix: Array<CharArray>,
        visited: Set<Point>
    ): Boolean {
        return x >= 0 && x < matrix.size && y >= 0 && y < matrix[0].size && matrix[x][y] == '1'
    }

}