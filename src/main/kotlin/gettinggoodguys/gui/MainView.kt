package gettinggoodguys.gui

import javafx.beans.property.Property
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleLongProperty
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import javafx.util.converter.IntegerStringConverter
import javafx.util.converter.LongStringConverter
import kotlinx.coroutines.*
import tornadofx.*

class MainView : View("Getting Good Guys - by BUCKFAE") {

    val controller: MainViewController by inject()

    val loopTime = SimpleLongProperty(500)
    val stepsPerGuiUpdate = SimpleIntegerProperty(1)

    var autoStepper: Job? = null

    override val root = borderpane {
        //this.setMinSize(500.0, 500.0)
        this.useMaxSize = true

        center {
            scrollpane {
                paddingAll = 5.0
                borderpaneConstraints { alignment = Pos.CENTER }

                isFitToWidth = true
                isFitToHeight = true
                // Style of the pane
                style {
                    fontWeight = FontWeight.EXTRA_BOLD
                    borderColor += box(
                        top = Color.RED,
                        right = Color.DARKGREEN,
                        left = Color.ORANGE,
                        bottom = Color.PURPLE
                    )
                }
                flowpane {
                    alignment = Pos.CENTER
                    hgap = 5.0
                    vgap = 5.0

                    // Creating and updating gameData
                    controller.initGameData()

                    for (dataGridID in 0 until controller.gamesToDisplay) {
                        controller.drawGame(dataGridID, this)
                    }
                }
            }

        }


        bottom = vbox {
            val stepButton = button(" Step ") {
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
            val hBox = hbox {
                useMaxWidth = true
                val autoStepButton = button("autoStep") {
                    useMaxWidth = true
                    action {
                        stepButton.isDisable = !stepButton.isDisable
                        val stepper = autoStepper
                        runBlocking {
                            if (stepper == null) {
                                autoStepper = GlobalScope.launch {
                                    while (true) {
                                        val start = System.currentTimeMillis()
                                        for (i in 1..stepsPerGuiUpdate.value) {
                                            controller.stepGames()
                                        }
                                        runAsync {
                                        } ui {
                                            controller.updateGames()
                                        }
                                        val took = System.currentTimeMillis() - start
                                        val delay = loopTime.get() - took
                                        if (delay > 0) {
                                            println("[debug]> Delay: $delay")
                                            delay(delay)
                                        } else {
                                            println("[debug]> delay ${loopTime.get()} vs $took")
                                        }
                                    }
                                }
                            } else {
                                stepper.cancelAndJoin()
                                autoStepper = null
                            }
                        }
                    }
                }
                label("Mean delay") { }
                val autoStepInterval = textfield("500") {
                    filterInput {
                        val text = it.controlNewText
                        text.isEmpty() || (text.isLong() && text.toLong() >= 0)
                    }
                    textProperty().bindBidirectional(loopTime as Property<Long>, LongStringConverter())
                }
                label("ms") { }
                label("Steps per guiUpdate") { }
                textfield("1") {
                    filterInput {
                        val text = it.controlNewText
                        text.isEmpty() || (text.isInt() && text.toInt() >= 0)
                    }
                    textProperty().bindBidirectional(stepsPerGuiUpdate as Property<Int>, IntegerStringConverter())
                }
            }

        }
    }
}
