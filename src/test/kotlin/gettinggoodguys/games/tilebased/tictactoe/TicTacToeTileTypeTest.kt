package gettinggoodguys.games.tilebased.tictactoe

import gettinggoodguys.games.tilebased.snake.SnakeTileType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TicTacToeTileTypeTest {

    @Test
    fun canBeOverriddenByTest() {

        // Testing EMPTY_TILE
        assertFalse(TicTacToeTileType.EMPTY_TILE.canBeOverriddenBy(TicTacToeTileType.EMPTY_TILE))
        assertTrue(TicTacToeTileType.EMPTY_TILE.canBeOverriddenBy(TicTacToeTileType.PLAYER_1_TILE))
        assertTrue(TicTacToeTileType.EMPTY_TILE.canBeOverriddenBy(TicTacToeTileType.PLAYER_2_TILE))
        assertFalse(TicTacToeTileType.EMPTY_TILE.canBeOverriddenBy(SnakeTileType.EMPTY_TILE))
        assertFalse(TicTacToeTileType.EMPTY_TILE.canBeOverriddenBy(SnakeTileType.SNAKE_BODY_TILE))

        // Testing PLAYER_1_TILE
        assertFalse(TicTacToeTileType.PLAYER_1_TILE.canBeOverriddenBy(TicTacToeTileType.EMPTY_TILE))
        assertFalse(TicTacToeTileType.PLAYER_1_TILE.canBeOverriddenBy(TicTacToeTileType.PLAYER_1_TILE))
        assertFalse(TicTacToeTileType.PLAYER_1_TILE.canBeOverriddenBy(TicTacToeTileType.PLAYER_2_TILE))
        assertFalse(TicTacToeTileType.PLAYER_1_TILE.canBeOverriddenBy(SnakeTileType.EMPTY_TILE))
        assertFalse(TicTacToeTileType.PLAYER_1_TILE.canBeOverriddenBy(SnakeTileType.SNAKE_BODY_TILE))

        // Testing PLAYER_2_TILE
        assertFalse(TicTacToeTileType.PLAYER_2_TILE.canBeOverriddenBy(TicTacToeTileType.EMPTY_TILE))
        assertFalse(TicTacToeTileType.PLAYER_2_TILE.canBeOverriddenBy(TicTacToeTileType.PLAYER_1_TILE))
        assertFalse(TicTacToeTileType.PLAYER_2_TILE.canBeOverriddenBy(TicTacToeTileType.PLAYER_2_TILE))
        assertFalse(TicTacToeTileType.PLAYER_2_TILE.canBeOverriddenBy(SnakeTileType.EMPTY_TILE))
        assertFalse(TicTacToeTileType.PLAYER_2_TILE.canBeOverriddenBy(SnakeTileType.SNAKE_BODY_TILE))
    }

    @Test
    fun toStringTest() {
        assertEquals(" ", TicTacToeTileType.EMPTY_TILE.toString())
        assertEquals("X", TicTacToeTileType.PLAYER_1_TILE.toString())
        assertEquals("O", TicTacToeTileType.PLAYER_2_TILE.toString())
    }
}