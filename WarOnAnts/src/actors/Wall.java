package actors;

/**
 * A wall that blocks ants and plants can be placed on this. Has a location in
 * the grid and can be placed in the grid or removed whenever the player wants
 * 
 * @author Dhruv Masurekar
 */

public class Wall {

    private int row, col;

    /**
     * Creates wall and initializes locations for the wall
     * @param r Row location of this wall
     * @param c Column location of this wall
     */
    public Wall(int r, int c) {
        row = r;
        col = c;
    }
    /**
     * Returns the row of this wall in the grid
     * @return the row of this wall
     */
    public int getRow() {
        return row;
    }
    /**
     * Returns the column of this wall in the grid
     * @return the column of this wall
     */
    public int getCol() {
        return col;
    }
}
