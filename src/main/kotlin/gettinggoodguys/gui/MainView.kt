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

                rectangle(10, 10, 10, 10) {
                    fill = Color.ORANGE;
                }
                rectangle(20, 10, 10, 10) {
                    fill = Color.RED;
                }

                button(" Step ") {
                    action {
                        runAsync {
                            controller.stepGames() // Stepping all games
                        } ui {
                            controller.updateGames() // Updating all games on the ui
                        }
                    }
                    anchorpaneConstraints { leftAnchor = 5.0; bottomAnchor = 5.0}
                }
            }

        }

    }
}
