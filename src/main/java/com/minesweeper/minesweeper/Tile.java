package com.minesweeper.minesweeper;

public class Tile {
    public enum Type {
        GRASS,
        MINE
    }
    private Type tileType;
    private final int x;
    private final int y;
    private boolean isVisible = false;
    private boolean isMarked = false;

    public void toggleMarked() {
        isMarked = !isMarked;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void clickTile() {
        isVisible = true;

    }

    public boolean getVisibility() {
        return isVisible;
    }

    private int numOfAdjacentMines = 0;


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
        this.numOfAdjacentMines = -1;
    }

    public int getNumOfAdjacentMines() {
        return numOfAdjacentMines;
    }

    public void incrementNumOfAdjacentMines() {
        if (this.tileType != Type.MINE) {
            this.numOfAdjacentMines++;
        }
    }

    public CoordTuple getCoord() {
        return new CoordTuple(this.x, this.y);
    }

    public String toString() {
        return "X["+x+"] Y["+y+"] numOfAdjacent["+numOfAdjacentMines+"]";
    }

}
