import java.util.ArrayList;
/**
 * Creates a grid of squares which is used to determine the location of all the plants, ants,
 * and walls that are currently present in the game.
 * @author William Hyun
 * */
public class Grid {

    private ArrayList<Insect> insects;
    private ArrayList<Plant> plants;
    private ArrayList<Wall> walls;
    private Fruit fruit;

    /**
     * Creates a grid and initializes fields by adding the respective objects to them
     * */
    public Grid() {

    }

    /**
     * Removes the object that is at the inputed x and y coordinates
     * @param x X coordinate of the object to be removed
     * @param y Y coordinate of the object to be removed
     * */
    public void remove(int x, int y) {

    }

    /**
     * Adds an object at the inputed x and y coordinates
     * @param x X coordinate of the object to be added
     * @param y Y coordinate of the object to be added
     * @param type the object that is to be added, can be insect, plant or wall
     * */
    public void add(int x, int y, Object type) {

    }

}
