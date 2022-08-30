package com.minesweeper.minesweeper;

public class CoordTuple {
    public int x;
    public int y;
    public CoordTuple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Gets string rep of the coord
    public String toString() {
        return "X[" + String.valueOf(x) + "]   Y[" + String.valueOf(y)+"]";
    }

    // Custom hashcode for the coords - makes easier for using in a queue implementation for the BFS
    @Override
    public int hashCode()
    {
        return 31 * ((31 * String.valueOf(x).hashCode()) + String.valueOf(y).hashCode());
    }

    // Checks whether coords care equal by value instead of by object so coords representing the same position are
    // determined to be equal
    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        if (!(o instanceof CoordTuple)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        CoordTuple c = (CoordTuple) o;

        return (this.x == c.x) && (this.y == c.y);
    }
}
