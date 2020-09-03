package gettinggoodguys.gui

import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class MainView : View("Getting Good Guys - by BUCKFAE") {

    val controller: MainViewController by inject()

    override val root = borderpane {
        //this.setMinSize(500.0, 500.0)
        this.useMaxSize = true

        center {
            scrollpane {
                isFitToWidth = true
                isFitToHeight = true
                flowpane {

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
                        controller.drawGame(dataGridID, this)
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
