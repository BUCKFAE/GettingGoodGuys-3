package gettinggoodguys.gui

import gettinggoodguys.controller.ai.AIGameController
import gettinggoodguys.games.tilebased.TileBasedGame
import gettinggoodguys.loop.AIMainLoop
import javafx.beans.property.SimpleIntegerProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.EventTarget
import tornadofx.Controller
import java.lang.Integer.min

class MainViewController : Controller() {

    private val aiMainLoop = AIMainLoop()

    // Stores how many games we should display
    // TODO: Make this independent from the amount of games we have in total
    val gamesToDisplay = min(10, aiMainLoop.controllerArrayList.size)

    val gameSizeX = 3

    /**
     * TODO: Explain that you can't just remove / add lists but have to change entries in order for this to work
     */
    val gameData: ObservableList<ObservableList<String>> = FXCollections.observableArrayList()

    val gameIDs: MutableList<SimpleIntegerProperty> = ArrayList()

    fun drawGame(index: Int, target: EventTarget) {
        if (index !in gameData.indices) {
            return
        }
        aiMainLoop.controllerArrayList[index].game.drawGame(target, gameData[index], gameIDs[index])
    }

    fun stepGames() {
        aiMainLoop.stepLoop()
    }

    /**
     * Returns the [index]th living controller, if no such controller exists returns a dead controller with deadIndex = index - livingAmount
     */
    private fun getLivingThenDeadControllerIndex(index: Int): AIGameController? {
        //show alive games first then dead games
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

    private val controllers = ArrayList<AIGameController>()

    fun updateGames() {

        // Adding gameData for all Games
        for (currentControllerID in 0 until controllers.size) {

            // Storing the current game for easy access
            val currentTileBasedGame = controllers[currentControllerID].game as TileBasedGame

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
        updateLivingDeadControllers()
    }

    /**
     * update the controllers list so that we have as much living controllers as possible
     * and only if there are not enough to only display living fill it with dead ones
     */
    private fun updateLivingDeadControllers() {
        controllers.clear()
        for (index in 0 until gamesToDisplay) {
            val controller = getLivingThenDeadControllerIndex(index) ?: break
            controllers.add(controller)
            val gameID = controller.game.gameID ?: -1
            gameIDs[index].set(gameID)
        }
    }

    fun initGameData() {

        for (currentGameID in 0 until gamesToDisplay) {

            val currentGameDataArrayList: ObservableList<String> = FXCollections.observableArrayList()

            //get current controller, if no such controller exists break because there are no controllers left
            val currentController = getLivingThenDeadControllerIndex(currentGameID) ?: break
            // Storing the current game for easy access
            val currentTileBasedGame: TileBasedGame = currentController.game as TileBasedGame

            // Adding the info to the current gameData
            for (currentY in 0 until currentTileBasedGame.gameBoardSizeY) {
                for (currentX in 0 until currentTileBasedGame.gameBoardSizeX) {
                    currentGameDataArrayList.add(" ")
                }
            }

            gameData.add(currentGameDataArrayList)
            controllers.add(currentController)
            gameIDs.add(SimpleIntegerProperty(currentController.game.gameID ?: -1))
        }

        updateGames()
    }

}