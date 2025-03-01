package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.kotlinlessons.ChapterEight
import com.example.myapplication.kotlinlessons.ChapterEight.challenges
import com.example.myapplication.kotlinlessons.ChapterTen
import com.example.myapplication.kotlinlessons.ChapterTen.builtInLambdas
import com.example.myapplication.kotlinlessons.ChapterTen.builtInMapAssociateLambdas
import com.example.myapplication.kotlinlessons.ChapterTen.creatingLambdas
import com.example.myapplication.kotlinlessons.chapter12.Chapter12.getters
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ChapterEight.createArrays()
        challenges()
        getters()
        creatingLambdas()
        builtInMapAssociateLambdas()
        builtInLambdas()
        ChapterTen.challenges()

        SmallestDistance.driverFunction()
        EventManagerDriver.eventManagerDriver()
        TestingParallelCoroutines.driverFunction()
        HashMapOperations.driverFunction()

        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}