package gettinggoodguys

import gettinggoodguys.games.tilebased.snake.SnakeGame
import gettinggoodguys.games.tilebased.snake.SnakeTileType
import gettinggoodguys.gui.Main
import javafx.application.Application.launch

fun main(args: Array<String>) {

    println("The program has been started!")

    val snakeGame = SnakeGame(5, 7)
    snakeGame.getTileAt(1, 0).setTileType(SnakeTileType.SNAKE_TILE)
    snakeGame.getTileAt(2, 0).setTileType(SnakeTileType.SNAKE_TILE)
    snakeGame.getTileAt(2, 1).setTileType(SnakeTileType.SNAKE_TILE)
    snakeGame.getTileAt(2, 2).setTileType(SnakeTileType.SNAKE_TILE)

    println(snakeGame.toPrettyString())

    launch(Main::class.java, *args)
}