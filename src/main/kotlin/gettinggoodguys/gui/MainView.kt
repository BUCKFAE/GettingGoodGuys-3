package gettinggoodguys.gui

import javafx.scene.paint.Color
import tornadofx.*

class MainView : View("Getting Good Guys - by BUCKFAE") {
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
            }

        }
    }
}
