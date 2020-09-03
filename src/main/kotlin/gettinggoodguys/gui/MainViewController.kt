package gettinggoodguys.gui

import gettinggoodguys.controller.ai.AIGameController
import gettinggoodguys.games.tilebased.TileBasedGame
import gettinggoodguys.loop.AIMainLoop
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.EventTarget
import tornadofx.Controller

class MainViewController : Controller() {

    private val aiMainLoop = AIMainLoop()

    // Stores how many games we should display
    // TODO: Make this independent from the amount of games we have in total
    val gamesToDisplay = aiMainLoop.controllerArrayList.size

    val gameSizeX = 3

    /**
     * TODO: Explain that you can't just remove / add lists but have to change entries in order for this to work
     */
    val gameData: ObservableList<ObservableList<String>> = FXCollections.observableArrayList()

    fun drawGame(index: Int, target: EventTarget) {
        if (index !in gameData.indices) {
            return
        }
        aiMainLoop.controllerArrayList[index].game.drawGame(target, gameData[index])
    }

    fun stepGames() {
        aiMainLoop.stepLoop()
    }

    /**
     * Returns the [index]th living controller, if no such controller exists returns a dead controller with deadIndex = index - livingAmount
     */
    private fun getLivingThenDeadController(index: Int): AIGameController? {
        //show alive games first then dead games
        if (index !in gameData.indices) {
            return null
        }
        println("[debug]> Index: $index")
        if (index in aiMainLoop.aliveControllers.indices) {
            val index = aiMainLoop.aliveControllers[index]
            println("[debug]> AliveIndex: $index")
            return aiMainLoop.controllerArrayList[index]
        } else {
            val index = index - aiMainLoop.aliveControllers.size
            if (index in aiMainLoop.deadControllers.indices) {
                val index = aiMainLoop.deadControllers[index]
                println("[debug]> DeadIndex: $index")
                return aiMainLoop.controllerArrayList[index]
            } else {
                println("$index not in ${aiMainLoop.deadControllers.indices}")
            }
        }
        return null
    }

    fun updateGames() {

        // Adding gameData for all Games
        for (currentControllerID in 0 until gamesToDisplay) {

            // Storing the current game for easy access
            val currentTileBasedGame = aiMainLoop.controllerArrayList[currentControllerID].game as TileBasedGame

            var currentItemID = 0

            // Adding the info to the current gameData
            for (currentY in 0 until currentTileBasedGame.gameBoardSizeY) {
                for (currentX in 0 until currentTileBasedGame.gameBoardSizeX) {

                    var currentTileInfo = currentTileBasedGame.getTileAt(currentX, currentY).tileType.toString()

                    gameData[currentControllerID].removeAt(currentItemID)


                    if (!currentTileBasedGame.isAlive()) {
                        currentTileInfo = currentTileInfo.replace(" ", "X")
                    }

                    // Exchanging the old tileInfo with the new tile info
                    gameData[currentControllerID].add(currentItemID, currentTileInfo)



                    currentItemID++
                }

            }

        }

    }

    fun initGameData() {

        for (currentGameID in 0 until gamesToDisplay) {

            val currentGameDataArrayList: ObservableList<String> = FXCollections.observableArrayList()

            // Storing the current game for easy access
            val currentTileBasedGame: TileBasedGame =
                aiMainLoop.controllerArrayList[currentGameID].game as TileBasedGame


            // Adding the info to the current gameData
            for (currentY in 0 until currentTileBasedGame.gameBoardSizeY) {
                for (currentX in 0 until currentTileBasedGame.gameBoardSizeX) {
                    currentGameDataArrayList.add(" ")
                }
            }

            gameData.add(currentGameDataArrayList)
        }

        updateGames()
    }

}