package com.minesweeper.minesweeper;

import java.util.*;


public class MinesweeperField {
    int width;
    int height;
    HashMap<CoordTuple, Tile> tiles = new HashMap<>();

    public MinesweeperField(int w, int h, int numOfMines) {
        this.width = w;
        this.height = h;

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                Tile myTile = new Tile(x, y);
                CoordTuple t = new CoordTuple(x, y);
                tiles.put(t, myTile);
                System.out.println(myTile);
                CoordTuple t1 = new CoordTuple(x, y);
                System.out.println(tiles.get(t1));
                //System.out.println(myTile);
            }
        }
        System.out.println("finished placing in hashmap");

        // pick <numOfMines> random tiles, and set to mines
        ArrayList<Tile> arrayListOfTiles = new ArrayList<Tile>(tiles.values());
        Collections.shuffle(arrayListOfTiles);
        for (int i = 0; i<numOfMines; i++) {
            Tile currentTile = arrayListOfTiles.get(i);
            currentTile.setToMine();

            // todo get adjacent tiles and increment
            ArrayList<CoordTuple> coordsOfAdjacentTiles = getAdjacentCoords(currentTile.getCoord());
            for (CoordTuple c : coordsOfAdjacentTiles) {
                //System.out.println(c.toString());
                Tile t = tiles.get(c);
                //System.out.println(t);
                //t.incrementNumOfAdjacentMines();
            }

        }


        // todo check that every tile has a correct number assigned.

    }


    public ArrayList<CoordTuple> getAdjacentCoords(CoordTuple t) {
        ArrayList<CoordTuple> outputList = new ArrayList<CoordTuple>();
        for (int x = t.x-1; x <= t.x+1; x++) {
            for (int y = t.y-1; y <= t.y+1; y++) {
                // Check that not returning same coord
                if (x == t.x && y == t.y) {
                    continue;
                }

                // Check in bounds
                if (y<0 || y>=height || x<0 || x>=width) {
                    continue;
                }

                outputList.add(new CoordTuple(x, y));
            }
        }

        return outputList;
    }

}
