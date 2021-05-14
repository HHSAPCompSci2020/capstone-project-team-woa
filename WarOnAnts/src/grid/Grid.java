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

    private ArrayList<Insect> insects = new ArrayList<Insect>();
    private ArrayList<Plant> plants= new ArrayList<Plant>();
    private ArrayList<Wall> walls= new ArrayList<Wall>();
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
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'I') {
                    Insect ins = new Insect(i,j, grid);
                    insects.add(ins);
                } else if (grid[i][j] == 'P') {
                    Wall w = new Wall(i,j);
                    Plant p = new Plant(w);
                    plants.add(p);
                } else if (grid[i][j] == '#') {
                    Wall w = new Wall(i,j);
                    walls.add(w);
                }
            }
        }
        
    }

    /**
     * Removes the object that is at the inputed x and y coordinates
     * 
     * @param r row of the object to be removed
     * @param c column of the object to be removed
     */
    public void remove(int r, int c) {
        if (grid[r][c] == 'I') {
            for (int i = 0; i < insects.size(); i++) {
                if (insects.get(i).getRow() == r && insects.get(i).getCol() == c) {
                    insects.remove(i);
                }
            }
            grid[r][c] = '.';
        } else if (grid[r][c] == 'P') {
            for (int i = 0; i < plants.size(); i++) {
                if (plants.get(i).getRow() == r && plants.get(i).getCol() == c) {
                    plants.remove(i);
                }
            }
            grid[r][c] = 'W';
        } else if (grid[r][c] == 'W') {
            for (int i = 0; i < walls.size(); i++) {
                if (walls.get(i).getRow() == r && walls.get(i).getCol() == c) {
                    walls.remove(i);
                }
            }
            grid[r][c] = '.';
        }

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
        if (insects != null) {
            for (Insect i : insects) {
                i.findOptimalPath(i.getRow(), i.getCol(), grid);
                i.act();
            }
        }
    }

    public Insect returnInsect(int row, int col) {

        // Checks every insect in the grid to see if they are at (row,col)

        if (grid[row][col] == 'I') {
            for (int i = 0; i < insects.size(); i++) {
                if (insects.get(i).getRow() == row && insects.get(i).getCol() == col) {
                    // return this insect if it is the correct one
                    return insects.get(i);
                }
            }
        }
        return null;
    }
}
