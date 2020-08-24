package gettinggoodguys.gui

import gettinggoodguys.games.tilebased.TileBasedGame
import gettinggoodguys.loop.AIMainLoop
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.Controller

class MainViewController : Controller() {

    val aiMainLoop = AIMainLoop()

    // Stores how many games we should display
    // TODO: Make this independent from the amount of games we have in total
    val gamesToDisplay = aiMainLoop.controllerArrayList.size

    val gameSizeX = 3

     val gameData: ObservableList<ObservableList<String>> = FXCollections.observableArrayList()


    fun stepGames() {
        println("Stepping all games now")
        aiMainLoop.stepLoop()

    }

    fun updateGames() {

        initFieldData()

        // Adding gameData for all Games
        for ((counter, currentController) in aiMainLoop.controllerArrayList.withIndex()) {

            // Storing the current game for easy access
            val currentTileBasedGame: TileBasedGame = currentController.game as TileBasedGame

            // Adding the info to the current gameData
            for (currentY in 0 until currentTileBasedGame.gameBoardSizeY) {
                for (currentX in 0 until currentTileBasedGame.gameBoardSizeX) {
                    gameData[counter].add(currentTileBasedGame.getTileAt(currentX, currentY).tileType.toString())
                }
            }

        }

        println(gameData[0].toString())
    }

     fun initFieldData() {

        // Removing all old data
        gameData.clear()

        for (currentDataArrayList in 0 until gamesToDisplay) {
            val currentGameDataList: ObservableList<String> = FXCollections.observableArrayList<String>("yww", "ddf", "megayeet")
            gameData.add(currentGameDataList)
        }
    }
}