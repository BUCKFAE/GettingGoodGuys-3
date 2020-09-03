package gettinggoodguys.games

import javafx.collections.ObservableList
import javafx.event.EventTarget

interface Game {

    /**
     * Performs one step in the given Game
     */
    fun step(moveOption: MoveOptions)

    fun isAlive(): Boolean

    /**
     * This is used by the gui to draw the game on the gui
     */
    fun drawGame(target: EventTarget)
    fun drawGame(target: EventTarget, gameData: ObservableList<String>)
}