package gettinggoodguys.games.tilebased.tictactoe

import gettinggoodguys.games.tilebased.tile.TileType

enum class TicTacToeTileType : TileType {
    PLAYER_1_TILE {

        override fun canBeOverriddenBy(tileType: TileType): Boolean {
            return false
        }

        override fun extendedToString(): String {
            return "TicTacToeTile = ${toString()}"
        }

        override fun toString(): String {
            return "X"
        }
    },

    PLAYER_2_TILE {
        override fun canBeOverriddenBy(tileType: TileType): Boolean {
            return false
        }

        override fun extendedToString(): String {
            return "TicTacToeTile = ${toString()}"
        }

        override fun toString(): String {
            return "O"
        }
    },

    EMPTY_TILE {
        override fun canBeOverriddenBy(tileType: TileType): Boolean {
            if(tileType == PLAYER_1_TILE || tileType == PLAYER_2_TILE) return true
            return false
        }

        override fun extendedToString(): String {
            return "TicTacToeTile = ${toString()}"
        }

        override fun toString(): String {
            return " "
        }
    };
}