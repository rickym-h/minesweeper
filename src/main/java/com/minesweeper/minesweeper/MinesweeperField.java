package com.minesweeper.minesweeper;

import java.util.*;


public class MinesweeperField {
    int width;
    int height;

    Set<Tile> tiles = new HashSet<Tile>();

    public MinesweeperField(int w, int h, int numOfMines) {
        this.width = w;
        this.height = h;

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                Tile myTile = new Tile(x, y);
                tiles.add(myTile);
            }
        }

        // pick <numOfMines> random tiles, and set to mines
        ArrayList<Tile> arrayListOfTiles = new ArrayList<>(Arrays.asList(tiles.toArray(new Tile[0])));
        Collections.shuffle(arrayListOfTiles);
        for (int i = 0; i<numOfMines; i++) {
            arrayListOfTiles.get(i).setToMine();
        }
    }

    // todo implement functions to get numbers on tiles, and
}
