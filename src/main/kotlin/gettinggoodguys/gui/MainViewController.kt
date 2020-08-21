package gettinggoodguys.gui

import gettinggoodguys.games.tilebased.TileBasedGame
import gettinggoodguys.games.tilebased.snake.SnakeTileType
import gettinggoodguys.loop.AIMainLoop
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.Controller

class MainViewController : Controller() {

    val aiMainLoop = AIMainLoop()


    val gameSizeX = 3

    val numbers: ObservableList<String> = FXCollections.observableArrayList<String>()

    fun stepGames() {
        println("Stepping all games now")
        aiMainLoop.stepLoop()

    }

    fun updateGames() {

        //numbers.clear()

        for (currentController in aiMainLoop.controllerArrayList) {
            val currentTileBasedGame: TileBasedGame = currentController.game as TileBasedGame

            for (currentY in 0 until currentTileBasedGame.gameBoardSizeY) {
                for (currentX in 0 until currentTileBasedGame.gameBoardSizeX) {

                    when(currentTileBasedGame.getTileAt(currentX, currentY).tileType) {
                        SnakeTileType.SNAKE_HEAD_TILE -> numbers.add("H")
                        SnakeTileType.SNAKE_BODY_TILE -> numbers.add("S")
                        SnakeTileType.EMPTY_TILE -> numbers.add("X")
                    }
                }
            }

        }

        println(numbers.toString())

    }
}