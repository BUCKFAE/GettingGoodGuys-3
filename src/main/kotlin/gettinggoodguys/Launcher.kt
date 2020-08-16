package gettinggoodguys

import gettinggoodguys.games.tilebased.snake.SnakeGame
import gettinggoodguys.games.tilebased.tictactoe.TicTacToeTileType
import gettinggoodguys.games.tilebased.tile.IllegalTileTypeOverrideException
import gettinggoodguys.games.tilebased.tile.NoTileAtCoordinatesException
import gettinggoodguys.gui.Main
import javafx.application.Application.launch

fun main(args: Array<String>) {

    println("The program has been started!")

    val snakeGame = SnakeGame(5, 7)


    snakeGame.getTileAt(0,0).tileType = TicTacToeTileType.PLAYER_1_TILE

    launch(Main::class.java, *args)
}