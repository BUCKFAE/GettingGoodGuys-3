package gettinggoodguys.games.tilebased.snake.ai

import gettinggoodguys.controller.ai.random.RandomAIGameController
import gettinggoodguys.games.Game
import gettinggoodguys.games.MoveOptions
import gettinggoodguys.games.tilebased.snake.SnakeGame
import gettinggoodguys.games.tilebased.snake.SnakeMoveOptions
import kotlin.random.Random

class SnakeRandomAIGameController: RandomAIGameController() {

    override val game = SnakeGame(3, 5)

    override fun getNextMove(): MoveOptions {
        val randomMove = Random.nextInt(SnakeMoveOptions.values().size)
        return SnakeMoveOptions.values()[randomMove]
    }
}