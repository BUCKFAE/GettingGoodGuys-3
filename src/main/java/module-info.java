module GettingGoodGuys.main {

    requires javafx.controls;
    requires javafx.graphics;
    //requires javafx.fxml;

    requires tornadofx;
    requires kotlin.stdlib;
    opens gettinggoodguys.gui;

    exports gettinggoodguys to org.junit.platform.commons;
}