package gettinggoodguys.loop

import gettinggoodguys.controller.ai.AIGameController
import gettinggoodguys.games.tilebased.snake.ai.SnakeRandomAIGameController
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

class AIMainLoop : MainLoop {

    private val amountOfGames = 10

    val controllerArrayList = ArrayList<AIGameController>()
    val aliveControllers = ArrayList<Int>()
    val deadControllers = ArrayList<Int>()

    // TODO: This currently only creates random snake games
    init {
        for (currentGame in 1..amountOfGames) {
            val currentRandomSnakeController = SnakeRandomAIGameController()
            currentRandomSnakeController.game.gameID = currentGame
            controllerArrayList.add(currentRandomSnakeController)
            aliveControllers.add(currentGame - 1)
        }
        deadControllers.clear()

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

    var generateNewGeneration = false

    override fun stepLoop() {

        if (generateNewGeneration) {
            println("[Debug]> All games of the current generation are dead")

            // Makes the first controller create a new population based on the current one
            controllerArrayList[0].createNewGeneration(controllerArrayList)
            for (index in controllerArrayList.indices) {
                controllerArrayList[index].game.gameID = index + 1
            }
            aliveControllers.clear()
            deadControllers.clear()
            for (index in controllerArrayList.indices) {
                aliveControllers.add(index)
            }

            println("Successfully created a new generation")
            generateNewGeneration = false
            return
        }

        runBlocking {
            val request = launch(dispatcher) {
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
                deadControllers.add(index)
                iterator.remove()
            }
        }
        println(aliveControllers)

        if (allGamesAreDead) {
            generateNewGeneration = true
        }
    }


}