package gettinggoodguys

import gettinggoodguys.games.movement.directions.RelativeDirection
import gettinggoodguys.games.tilebased.snake.SnakeGame
import gettinggoodguys.games.tilebased.snake.SnakeMoveOptions
import gettinggoodguys.games.tilebased.snake.ai.SnakeRandomAIGameController
import gettinggoodguys.games.tilebased.tictactoe.TicTacToeTileType
import gettinggoodguys.games.tilebased.tile.IllegalTileTypeOverrideException
import gettinggoodguys.games.tilebased.tile.NoTileAtCoordinatesException
import gettinggoodguys.gui.Main
import gettinggoodguys.loop.AIMainLoop
import javafx.application.Application.launch

fun main(args: Array<String>) {

    println("The program has been started")
    println("Commandline arguments: ${args.contentToString()}")

    val aiMainLoop = AIMainLoop()

    aiMainLoop.stepLoop()



    launch(Main::class.java, *args)
}