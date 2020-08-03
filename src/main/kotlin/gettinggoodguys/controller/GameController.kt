package gettinggoodguys.controller

import gettinggoodguys.games.Game
import gettinggoodguys.games.MoveOptions

interface GameController {

    val game: Game

    fun getNextMove(): MoveOptions
}