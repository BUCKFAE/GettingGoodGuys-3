package gettinggoodguys.gui

import javafx.scene.control.Cell
import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*
import tornadofx.Stylesheet.Companion.cell

class MainView : View("Getting Good Guys - by BUCKFAE") {

    val controller: MainViewController by inject()

    override val root = borderpane {
        this.setMinSize(500.0, 500.0)

        this.useMaxSize

        center {
            borderpane {
                center = flowpane {

                    // Style of the flowpane
                    style {
                        fontWeight = FontWeight.EXTRA_BOLD
                        borderColor += box(
                            top = Color.RED,
                            right = Color.DARKGREEN,
                            left = Color.ORANGE,
                            bottom = Color.PURPLE
                        )
                    }

                    // Creating and updating gameData
                    controller.initGameData()

                    for (dataGridID in 0 until controller.gamesToDisplay) {

                        anchorpane() {
                            datagrid(controller.gameData[dataGridID]) {


                                style {
                                    fontWeight = FontWeight.EXTRA_BOLD
                                    borderColor += box(
                                        top = Color.RED,
                                        right = Color.DARKGREEN,
                                        left = Color.ORANGE,
                                        bottom = Color.PURPLE
                                    )


                                }

                                //TODO: Write code that centers all games

                                anchorpaneConstraints { leftAnchor = 5.0; topAnchor = 5.0 }

                                horizontalCellSpacing = 1.0
                                verticalCellSpacing = 1.0

                                cellHeight = 50.0
                                cellWidth = 50.0


                                prefWidth = controller.gameSizeX * cellHeight + 50

                                maxCellsInRow = controller.gameSizeX

                                // The actual cell value
                                cellFormat {
                                    graphic = cache {
                                        label(it) { }
                                    }
                                }
                            }
                        }
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
                                // anchorpaneConstraints { bottomAnchor = 5.0 }
                            }

                        }

                    }
                }

            }

        }

    }
}
