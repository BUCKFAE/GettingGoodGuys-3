package gettinggoodguys.games

import javafx.beans.property.IntegerProperty
import javafx.collections.ObservableList
import javafx.event.EventTarget

interface Game {

    var gameID: Int?

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
    fun drawGame(target: EventTarget, gameData: ObservableList<String>, gameID: IntegerProperty?)
}