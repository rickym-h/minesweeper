package com.minesweeper.minesweeper;

public class CoordTuple {
    public int x;
    public int y;
    public CoordTuple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "X[" + String.valueOf(x) + "]   Y[" + String.valueOf(y)+"]";
    }

    @Override
    public int hashCode()
    {
        return 31 * ((31 * String.valueOf(x).hashCode()) + String.valueOf(y).hashCode());
    }

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
