package gettinggoodguys.games.tilebased.snake

import gettinggoodguys.games.movement.directions.RelativeDirection
import org.junit.jupiter.api.Test

internal class SnakeGameTest {

    @Test
    fun getDefaultTileTypeTest() {
    }

    @Test
    fun stepTest() {
        val snakeGame = SnakeGame(3, 7)

        snakeGame.moveToRelativeDir(RelativeDirection.AHEAD)

        snakeGame.moveToRelativeDir(RelativeDirection.LEFT)

        snakeGame.moveToRelativeDir(RelativeDirection.LEFT)
    }

    @Test
    fun isAliveTest() {
    }


}