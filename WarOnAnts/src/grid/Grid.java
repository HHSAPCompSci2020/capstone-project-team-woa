package grid;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;

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


    private ArrayList<Insect> insects = new ArrayList<Insect>();
    private ArrayList<Plant> plants= new ArrayList<Plant>();
    private ArrayList<Wall> walls= new ArrayList<Wall>();
    private Fruit fruit;

    
    /**
     * Creates a Grid object and copies the information from the file given.
     * @param filename the name of the map file being read
     */
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
                } else if(grid[i][j] == 'X') {
                    fruit = new Fruit(100,i,j);
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
            grid[r][c] = '#';
        } else if (grid[r][c] == '#') {
            for (int i = 0; i < walls.size(); i++) {
                if (walls.get(i).getRow() == r && walls.get(i).getCol() == c) {
                    walls.remove(i);
                }
            }
            grid[r][c] = '.';
        }

    }
    
    /**
     * Toggles between a wall and a open space.
     * 
     * @param r row of the location to be toggled
     * @param c column of the location to be toggled
     */
    public void toggleWall(int r, int c) {
        if (grid[r][c] == '#') {
            for (int i = 0; i < walls.size(); i++) {
                if (walls.get(i).getRow() == r && walls.get(i).getCol() == c) {
                    walls.remove(i);
                }
            }
            grid[r][c] = '.';
        } else if (grid[r][c] == '.') {
            grid[r][c] = '#';
            walls.add(new Wall(r,c));
        }

    }

    /**
     * Adds an object at the inputed x and y coordinates
     * 
     * @param r    Row location of the object to be added
     * @param c    Column location of the object to be added
     * @param type the object that is to be added, can be insect, plant or wall
     */
    public void add(int r, int c, Object type) {
        if (type instanceof Wall) {
            walls.add((Wall)type);
            grid[r][c] = '#';
            System.out.println("hi");
        } else if (type instanceof Insect) {
            insects.add((Insect)type);
            grid[r][c] = 'I';
        } else if (type instanceof Plant) {
            plants.add((Plant)type);
            grid[r][c] = 'P';
        }
    }
    
    class SortByLength implements Comparator<Insect>
    {
        public int compare(Insect a, Insect b)
        {
            return a.getPathLen() - b.getPathLen();
        }
    }
    
    /**
     * Starts and continues to run the program
     */
    public void act() {
        if (insects != null) {
            for (Insect i : insects) {
                i.findOptimalPath(i.getRow(), i.getCol(), grid);
            }
 
            insects.sort(new SortByLength());
            for (Insect i : insects) {
                i.act();
            }
        }
    }
    
}
