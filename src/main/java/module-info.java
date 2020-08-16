module GettingGoodGuys.main {

    requires javafx.controls;
    requires javafx.graphics;
    //requires javafx.fxml;

    requires tornadofx;
    requires kotlin.stdlib;
    opens gettinggoodguys.gui;

    exports gettinggoodguys to org.junit.platform.commons;
    exports gettinggoodguys.games.tilebased to org.junit.platform.commons;

    exports gettinggoodguys.games.tilebased.tictactoe to org.junit.platform.commons;
    exports gettinggoodguys.games.tilebased.snake to org.junit.platform.commons;
    exports gettinggoodguys.games.tilebased.tile to org.junit.platform.commons;

    exports gettinggoodguys.games.movement.directions to org.junit.platform.commons;
}