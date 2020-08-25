package gettinggoodguys.games.tilebased.snake

import gettinggoodguys.games.tilebased.tile.TileType

//TODO: An empty tile cannot be overriden by a body tile, only by head / food.
enum class SnakeTileType: TileType {
    SNAKE_BODY_TILE {
        override fun canBeOverriddenBy(tileType: TileType): Boolean {
            if(tileType is SnakeTileType) return true
            return false
        }

        override fun extendedToString(): String {
            return "SnakeTile = ${toString()}"
        }

        override fun toString(): String {
            return "S"
        }
    },
    SNAKE_HEAD_TILE {
        override fun canBeOverriddenBy(tileType: TileType): Boolean {
            if(tileType is SnakeTileType) return true
            return false
        }

        override fun extendedToString(): String {
            return "SnakeTile = ${toString()}"
        }

        override fun toString(): String {
            return "H"
        }
    },
    FOOD_TILE {
        override fun canBeOverriddenBy(tileType: TileType): Boolean {
            if(tileType is SnakeTileType) return true
            return false
        }

        override fun extendedToString(): String {
            return "SnakeTile = ${toString()}"
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

        override fun extendedToString(): String {
            return "SnakeTile = ${toString()}"
        }

        override fun toString(): String {
            return " "
        }
    };
}