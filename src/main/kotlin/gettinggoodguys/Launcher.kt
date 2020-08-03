package gettinggoodguys

import gettinggoodguys.games.Game
import gettinggoodguys.games.movement.Controller
import gettinggoodguys.games.tilebased.snake.SnakeGame
import gettinggoodguys.games.tilebased.snake.SnakeTileType
import gettinggoodguys.games.tilebased.tictactoe.TicTacToeMoveOptions
import gettinggoodguys.gui.Main
import javafx.application.Application.launch

fun main(args: Array<String>) {

    println("The program has been started!")

    val snakeGame = SnakeGame(5, 7)
    snakeGame.getTileAt(1, 0).tileType = SnakeTileType.SNAKE_TILE
    snakeGame.getTileAt(2, 0).tileType = SnakeTileType.SNAKE_TILE
    snakeGame.getTileAt(2, 1).tileType = SnakeTileType.SNAKE_TILE
    snakeGame.getTileAt(2, 2).tileType = SnakeTileType.SNAKE_TILE

    val mo = TicTacToeMoveOptions(2, 5)
    println(mo.toString())

    val controller = Controller(Game.GameTypes.Snake)

    println(snakeGame.toPrettyString())

    launch(Main::class.java, *args)
}