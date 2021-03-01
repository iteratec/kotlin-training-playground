package de.iteratec.kotlin_training_playground

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


suspend fun helloWorld() = coroutineScope {
    launch {
        delay(1000)
        println("Kotlin Coroutines World!")
    }
    println("Hello")
}


suspend fun takeThisJava() = coroutineScope {
    repeat(1_000_000) {
        // launch a lot of coroutines
        launch {
            delay(1000L)
            print(".")
        }
    }
}


// Disabled because JavaFX is not available in every JDK 8 package - uncomment for yourself
//  to see if the javafx.* imports work

//class HelloJavaFX : Application() {
//
//    override fun start(primaryStage: Stage) {
//        primaryStage.apply {
//            val label = Label("Say 'Hello World'")
//            scene = Scene(VBox(label)).apply {
//                stylesheets.add(getResourceUri("/javafx.css"))
//            }
//            show()
//
//
//            // this is where the magic happens
//            GlobalScope.launch(Dispatchers.Main) {
//                delay(1000)
//
//                label.text = "This"
//                delay(1000)
//
//                label.text = "is"
//                delay(1000)
//
//                label.text = "Kotlin!"
//            }
//
//        }
//    }
//
//}

private fun getResourceUri(resourceName: String): String =
    object {}.javaClass.getResource(resourceName).toExternalForm()


fun main() {
    runBlocking {
        helloWorld()
        // takeThisJava()
    }
//    Application.launch(HelloJavaFX::class.java)
}
