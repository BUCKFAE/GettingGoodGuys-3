package gettinggoodguys

import gettinggoodguys.gui.Main
import javafx.application.Application.launch

fun main(args: Array<String>) {

    println("The program has been started!")

    launch(Main::class.java, *args)
}