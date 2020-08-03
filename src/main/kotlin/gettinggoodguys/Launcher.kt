package gettinggoodguys

import gettinggoodguys.games.Game
import gettinggoodguys.games.movement.Controller
import gettinggoodguys.games.movement.directions.RelativeDirection
import gettinggoodguys.games.tilebased.snake.SnakeGame
import gettinggoodguys.games.tilebased.snake.SnakeTileType
import gettinggoodguys.games.tilebased.tictactoe.TicTacToeMoveOptions
import gettinggoodguys.gui.Main
import javafx.application.Application.launch

fun main(args: Array<String>) {

    println("The program has been started!")

    val snakeGame = SnakeGame(5, 7)

    println(snakeGame.toPrettyString())

    while (true) {
        var dir = readLine()
        when (dir) {
            "w" -> snakeGame.moveToRelativeDir(RelativeDirection.AHEAD)
            "a" -> snakeGame.moveToRelativeDir(RelativeDirection.LEFT)
            "d" -> snakeGame.moveToRelativeDir(RelativeDirection.RIGHT)
        }
        println(snakeGame.toPrettyString())
    }

    launch(Main::class.java, *args)
}