package com.minesweeper.minesweeper;

public class Tile {
    public enum Type {
        GRASS,
        MINE
    }
    private Type tileType;
    private final int x;
    private final int y;

    // todo add a bool to show if the tile is shown or not. make toggleable

    // todo add slot to show number on tile


    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.tileType = Type.GRASS;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Type getTileType() {
        return tileType;
    }

    public void setToMine() {
        this.tileType = Type.MINE;
    }

}
