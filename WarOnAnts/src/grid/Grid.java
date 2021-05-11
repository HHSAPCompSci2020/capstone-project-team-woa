package grid;

import java.awt.Point;
import java.util.ArrayList;

import actors.Fruit;
import actors.Insect;
import actors.Plant;
import actors.Wall;
import template.GridTemplate;

/**
 * Creates a grid of squares which is used to determine the location of all the
 * plants, ants, and walls that are currently present in the game.
 * 
 * @author William Hyun and co-author Dhruv Masurekar
 */
public class Grid extends GridTemplate {

    private char[][] grid2 = new char[20][20];

    private Point p;

    private ArrayList<Insect> insects;
    private ArrayList<Plant> plants;
    private ArrayList<Wall> walls;
    private Fruit fruit;

    /**
     * Creates a grid and initializes fields by adding the respective objects to
     * them
     */
    public Grid() {
        super();
    }

    public Grid(String filename) {
        super(20, 20, filename);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid2[i][j] = grid[i][j];
            }
        }
    }

    /**
     * Removes the object that is at the inputed x and y coordinates
     * 
     * @param x X coordinate of the object to be removed
     * @param y Y coordinate of the object to be removed
     */
    public void remove(int x, int y) {

    }

    /**
     * Adds an object at the inputed x and y coordinates
     * 
     * @param x    X coordinate of the object to be added
     * @param y    Y coordinate of the object to be added
     * @param type the object that is to be added, can be insect, plant or wall
     */
    public void add(int x, int y, Object type) {

    }

    public void act() {

    }

    public Insect returnInsect(int row, int col) {
        //Checks every insect in the grid to see if they are at (row,col)
        for(int i=0;i<insects.size();i++) {
            if(insects.get(i).getY()==row&&insects.get(i).getX()==col) {
                //return this insect if it is the correct one
                return insects.get(i);
            }
        }
        //return null if no such insect exists.
        return null;
    }
}