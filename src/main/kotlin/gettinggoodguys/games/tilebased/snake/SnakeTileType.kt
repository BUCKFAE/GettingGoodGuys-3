package gettinggoodguys.games.tilebased.snake

import gettinggoodguys.games.tilebased.TileType

enum class SnakeTileType: TileType {
    SNAKE_BODY_TILE {
        override fun canBeOverriddenBy(tileType: TileType): Boolean {
            if(tileType is SnakeTileType) return true
            return false
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

        override fun toString(): String {
            return "H"
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