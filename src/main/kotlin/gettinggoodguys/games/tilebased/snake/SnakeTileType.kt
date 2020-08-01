package gettinggoodguys.games.tilebased.snake

import gettinggoodguys.games.tilebased.TileType
import gettinggoodguys.games.tilebased.tictactoe.TicTacToeTileType

enum class SnakeTileType: TileType {
    SNAKE_TILE {
        override fun canBeOverriddenBy(tileType: TileType): Boolean {
            if(tileType is SnakeTileType) return true
            return false
        }

        override fun toString(): String {
            return "S"
        }
    },
    FOOD_TILE {
        override fun canBeOverriddenBy(tileType: TileType): Boolean {
            if(tileType is SnakeTileType) return true
            return false
        }

        override fun toString(): String {
            return "F"
        }
    },
    EMPTY_TILE {
        override fun canBeOverriddenBy(tileType: TileType): Boolean {
            if(tileType is SnakeTileType) return true
            return false
        }

        override fun toString(): String {
            return " "
        }
    };
}