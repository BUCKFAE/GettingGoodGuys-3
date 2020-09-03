package gettinggoodguys.loop

import gettinggoodguys.controller.ai.AIGameController
import gettinggoodguys.games.tilebased.snake.ai.SnakeRandomAIGameController
import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

class AIMainLoop : MainLoop {

    private val amountOfGames = 5

    val controllerArrayList = ArrayList<AIGameController>()
    val aliveControllers = ArrayList<Int>()

    // TODO: This currently only creates random snake games
    init {
        for (currentGame in 1..amountOfGames) {
            val currentRandomSnakeController = SnakeRandomAIGameController()
            controllerArrayList.add(currentRandomSnakeController)
            aliveControllers.add(currentGame - 1)
        }

        println("The AI Main Loop has been initialized")

    }

    override fun startLoop() {
        TODO("Not yet implemented")
    }

    override fun stopLoop() {
        TODO("Not yet implemented")
    }

    val threads = 4
    val dispatcher = Executors.newScheduledThreadPool(threads).asCoroutineDispatcher()

    override fun stepLoop() {

        runBlocking {
            val request = launch {
                println("[Debug]> All games start stepping")
                for (index in aliveControllers) {
                    launch(dispatcher) {
                        println("[Debug]> Game $index started stepping")
                        val currentGameController = controllerArrayList[index]
                        val nextMove = currentGameController.getNextMove()
                        currentGameController.game.step(nextMove)
                        println("[Debug]> Game $index finished stepping")
                    }
                }
                println("[Debug]> Stepping scheduling finished")
            }
            request.join()
            println("[Debug]> All games stepped")
        }

        // Checking if all games are dead
        var allGamesAreDead = true

        //check for dead ones
        val iterator = aliveControllers.listIterator()
        while (iterator.hasNext()) {
            val index = iterator.next()
            if (controllerArrayList[index].game.isAlive()) {
                allGamesAreDead = false
            } else {
                println("[Debug]> $index was dead")
                iterator.remove()
            }
        }
        println(aliveControllers)

        if (allGamesAreDead) {
            println("[Debug]> All games of the current generation are dead")

            // Makes the first controller create a new population based on the current one
            controllerArrayList[0].createNewGeneration(controllerArrayList)
            aliveControllers.clear()
            for (index in controllerArrayList.indices) {
                aliveControllers.add(index)
            }

            println("Successfully created a new generation")

        }
    }


}