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
        println(snakeGame.toPrettyString())

        snakeGame.moveToRelativeDir(RelativeDirection.AHEAD)
        println(snakeGame.toPrettyString())

        snakeGame.moveToRelativeDir(RelativeDirection.LEFT)
        println(snakeGame.toPrettyString())

        snakeGame.moveToRelativeDir(RelativeDirection.LEFT)
        println(snakeGame.toPrettyString())
    }

    @Test
    fun isAliveTest() {
    }


}