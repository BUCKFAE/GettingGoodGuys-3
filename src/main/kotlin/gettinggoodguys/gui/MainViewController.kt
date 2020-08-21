package gettinggoodguys.gui

import gettinggoodguys.loop.AIMainLoop
import tornadofx.Controller

class MainViewController: Controller() {

    val aiMainLoop = AIMainLoop()

    fun stepGames() {
        println("Stepping all games now")
        aiMainLoop.stepLoop()

    }

    fun updateGames() {
        println("Updating the games on the ui now")
    }
}