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
            }
        }
        System.out.println("finished placing in hashmap");

        // pick <numOfMines> random tiles, and set to mines
        ArrayList<Tile> arrayListOfTiles = new ArrayList<Tile>(tiles.values());
        Collections.shuffle(arrayListOfTiles);
        for (int i = 0; i<numOfMines; i++) {
            Tile currentTile = arrayListOfTiles.get(i);
            currentTile.setToMine();

            ArrayList<CoordTuple> coordsOfAdjacentTiles = getAdjacentCoords(currentTile.getCoord());
            for (CoordTuple c : coordsOfAdjacentTiles) {
                Tile t = tiles.get(c);
                //System.out.println(t);
                t.incrementNumOfAdjacentMines();
            }

        }



        int[][] matrix = new int[height][width];
        for (Tile t : tiles.values()) {
            matrix[t.getY()][t.getX()] = t.getNumOfAdjacentMines();
        }


        for (int[] rows : matrix) {
            // length returns number of rows
            for (int cols : rows) {
                // here length returns number of columns corresponding to current row
                // using tabs for equal spaces, looks better aligned
                // matrix[i][j] will return each element placed at row ‘i',column 'j'
                System.out.print(cols + "\t");
            }
            System.out.println();
        }

    }

    public void clickTile(CoordTuple t) {
        tiles.get(t).clickTile();
        if (tiles.get(t).getNumOfAdjacentMines() != 0) {
            return;
        }
        // if any adjacent tiles are 0, add to set recursively.
        Queue<Tile> BFSQueue = new LinkedList<Tile>();
        BFSQueue.add(tiles.get(t));

        Set<Tile> adjacentZeroTiles = new HashSet<Tile>();
        while (!BFSQueue.isEmpty()) {
            // process top node and remove from queue
            Tile frontTile = BFSQueue.remove();
            // get adjacent valid tiles, and add to queue
            ArrayList<CoordTuple> surroundingTiles = getAdjacentCoords(frontTile.getCoord());
            for (CoordTuple c : surroundingTiles) {
                Tile currentTile = tiles.get(c);
                if (currentTile.getVisibility()) {
                    continue;
                }
                if (currentTile.getNumOfAdjacentMines() == 0) {
                    currentTile.clickTile();
                    BFSQueue.add(currentTile);
                }
            }
            // add front tile to set.
            adjacentZeroTiles.add(frontTile);
        }

        // for every tile in set, add all adjacent tiles to new set.
        Set<Tile> borderTiles = new HashSet<Tile>();
        for (Tile currentTile : adjacentZeroTiles) {
            ArrayList<CoordTuple> coords = getAdjacentCoords(currentTile.getCoord());
            for (CoordTuple c : coords) {
                //borderTiles.add(tiles.get(c));
                tiles.get(c).clickTile();
            }
        }

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
