package actors;
import java.awt.*;
import java.util.ArrayList;

/**
 * An insect who starts from the beginning of the maze and goes to the end. If
 * insect reaches the fruit and eats it completely, the game ends
 * 
 * @author William Hyun
 */

public class Insect {

    protected ArrayList<Point> optimalPath;
    protected int xCoor, yCoor;

    /**
     * Creates an insect and initializes the initial coordinates
     * 
     */
    public Insect() {

    }

    /**
     * Finds and returns the shortest path from the input location to the fruit.
     * 
     * @param x X coordinate of the location
     * @param y Y coordinate of the location
     * @return returns the points which the insect must take to get to the fruit
     *         fastest
     */
    public ArrayList<Point> findOptimalPath(int x, int y) {
        return null;
    }
}
