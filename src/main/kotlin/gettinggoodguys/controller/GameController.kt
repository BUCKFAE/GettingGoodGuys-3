package gettinggoodguys.controller

import gettinggoodguys.games.Game
import gettinggoodguys.games.MoveOptions

interface GameController {

    var game: Game

    fun getNextMove(): MoveOptions
}