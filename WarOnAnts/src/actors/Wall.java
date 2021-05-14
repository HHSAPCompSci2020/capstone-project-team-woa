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
     * 
     */
    public Wall(int r, int c) {
        row = r;
        col = c;
    }

    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
}
