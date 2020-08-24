package gettinggoodguys.gui

import javafx.scene.control.Cell
import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*
import tornadofx.Stylesheet.Companion.cell

class MainView : View("Getting Good Guys - by BUCKFAE") {

    val controller: MainViewController by inject()

    init {
        controller.initFieldData()
    }

    override val root = borderpane {
        this.setMinSize(500.0, 500.0)

        this.resize(500.0, 500.0)

        center {
            borderpane {
                center = vbox {

                    style {
                        fontWeight = FontWeight.EXTRA_BOLD
                        borderColor += box(
                            top = Color.RED,
                            right = Color.DARKGREEN,
                            left = Color.ORANGE,
                            bottom = Color.PURPLE
                        )
                    }


                    println("Cration gtd ${controller.gamesToDisplay}")
                    //for (dataGridID in 0 until controller.gamesToDisplay) {

                        println("${controller.gameData[0]}")
                        datagrid(controller.gameData[0]) {


                            style {
                                fontWeight = FontWeight.EXTRA_BOLD
                                borderColor += box(
                                    top = Color.RED,
                                    right = Color.DARKGREEN,
                                    left = Color.ORANGE,
                                    bottom = Color.PURPLE
                                )
                            }

                            cellHeight = 100.0
                            cellWidth = 100.0

                            maxCellsInRow = controller.gameSizeX

                            cellFormat {
                                println("Curr $it")
                                //text = "$it"
                                graphic = cache {
                                    label(it) { }
                                }
                            }

                            //anchorpaneConstraints { rightAnchor = 600 * (dataGridID + 1) }
                        //}
                    }


                    bottom = borderpane {
                        center = vbox {
                            button(" Step ") {
                                useMaxWidth = true
                                action {
                                    runAsync {
                                        controller.stepGames() // Stepping all games
                                    } ui {
                                        controller.updateGames() // Updating all games on the ui
                                    }
                                }
                                //anchorpaneConstraints { bottomAnchor = 5.0 }
                            }
                        }

                    }
                }

            }

        }

    }
}
