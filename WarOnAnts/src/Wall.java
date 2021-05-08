
/**
 * A wall that blocks ants and plants can be placed on this. Has a location in
 * the grid and can be placed in the grid or removed whenever the player wants
 * 
 * @author Dhruv Masurekar
 */

public class Wall {

    private int xCoor, yCoor;

    /**
     * Creates wall and initializes locations for the wall
     * 
     */
    public Wall() {

    }

    public int getX() {
        return xCoor;
    }
    
    public int getY() {
        return yCoor;
    }
}
