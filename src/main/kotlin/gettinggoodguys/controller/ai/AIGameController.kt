package gettinggoodguys.controller.ai

import gettinggoodguys.controller.GameController

interface AIGameController: GameController {

    fun createNewGeneration(controllerArrayList: ArrayList<AIGameController>)
}