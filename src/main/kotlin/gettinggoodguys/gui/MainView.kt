package gettinggoodguys.gui

import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import tornadofx.*

class MainView : View("Getting Good Guys - by BUCKFAE") {

    val controller: MainViewController by inject()


    override val root = borderpane {
        this.setMinSize(500.0, 100.0)

        center {
            anchorpane() {

                datagrid(controller.numbers) {
                    cellHeight = 40.0
                    cellWidth = 40.0

                    maxCellsInRow = controller.gameSizeX

                    cellCache {
                        println("Curr $it")
                            label(it.toString())

                    }
                }



                button(" Step ") {
                    action {
                        runAsync {
                            controller.stepGames() // Stepping all games
                        } ui {
                            controller.numbers.clear()
                            controller.updateGames() // Updating all games on the ui
                        }
                    }
                    anchorpaneConstraints { leftAnchor = 5.0; bottomAnchor = 5.0}
                }
            }

        }

    }
}
