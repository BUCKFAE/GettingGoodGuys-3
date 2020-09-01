package gettinggoodguys.loop

import gettinggoodguys.controller.ai.AIGameController
import gettinggoodguys.games.tilebased.snake.ai.SnakeRandomAIGameController

class AIMainLoop: MainLoop {

    private val amountOfGames = 2

    val currentGenIsDead = false

    val controllerArrayList = ArrayList<AIGameController>()

    // TODO: This currently only creates random snake games
    init {
        for (currentGame in 1..amountOfGames) {
            val currentRandomSnakeController = SnakeRandomAIGameController()
            controllerArrayList.add(currentRandomSnakeController)
        }

        println("The AI Main Loop has been initialized")

    }

    override fun startLoop() {
        TODO("Not yet implemented")
    }

    override fun stopLoop() {
        TODO("Not yet implemented")
    }

    override fun stepLoop() {

        // Checking if all games are dead
        var allGamesAreDead = true

        // TODO: This should be multithreaded!
        for(currentGameController in controllerArrayList) {
            if(currentGameController.game.isAlive()) {

                // Updating the game
                val nextMove = currentGameController.getNextMove()
                currentGameController.game.step(nextMove)

                // There is at least one game alive
                allGamesAreDead = false
            }
        }

        if(allGamesAreDead) {
            println("All games of the current generation are dead")

            // Makes the first controller create a new population based on the current one
            controllerArrayList[0].createNewGeneration(controllerArrayList)

            println("Successfully created a new generation")

        }
    }


}