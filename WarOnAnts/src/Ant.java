import java.awt.*;
import java.util.ArrayList;

/**
 * An ant who starts from the beginning of the maze and through it to the end.
 * When the ant finishes the maze and eats the plant, the game ends
 * 
 * @author William Hyun
 */
public class Ant extends Insect {

    public Ant() {
        super();
    }

    /**
     * Ant continues to act which includes changing location by following the
     * shortest path.
     */
    public void act() {

    }

    /**
     * Finds and returns the shortest path through the maze from the input location
     * to the fruit.
     * 
     * @param x X coordinate of the location
     * @param y Y coordinate of the location
     * @return returns the points which the ant must take to get to the fruit
     *         fastest
     */
    public ArrayList<Point> findOptimalPath(int x, int y) {
        return null;
    }
}
