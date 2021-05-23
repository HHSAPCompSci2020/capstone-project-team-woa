package grid;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;

import actors.Fruit;
import actors.Insect;
import actors.Plant;
import actors.Wall;
import processing.core.PApplet;
import template.GridTemplate;

/**
 * Creates a grid of squares which is used to determine the location of all the
 * plants, ants, and walls that are currently present in the game.
 * 
 * @author William Hyun and co-author Dhruv Masurekar
 */
public class Grid extends GridTemplate {

 //   private char[][] grid2 = new char[20][20];

    private ArrayList<Insect> insects = new ArrayList<Insect>();
    private ArrayList<Plant> plants = new ArrayList<Plant>();
    private ArrayList<Wall> walls = new ArrayList<Wall>();
    private Fruit fruit;
    public int antHoleRow;
    public int antHoleCol;
    private ArrayList<ArrayList<Float>> lines = new ArrayList<ArrayList<Float>>();
    
    public boolean gameOver = false;
    public String file;
    

    /**
     * Creates a Grid object and copies the information from the file given.
     * 
     * @param filename the name of the map file being read
     */
    public Grid(String filename) {
        super(11, 11, filename);
        file= filename;
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[0].length; j++) {
//                grid2[i][j] = grid[i][j];
//            }
//        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'I') {
                    Insect ins = new Insect(i, j, grid);
                    insects.add(ins);
                } else if (grid[i][j] == 'P') {
                    Wall w = new Wall(i, j);
                    Plant p = new Plant(w);
                    plants.add(p);
                } else if (grid[i][j] == '#') {
                    Wall w = new Wall(i, j);
                    walls.add(w);
                } else if (grid[i][j] == 'X') {
                    fruit = new Fruit(100, i, j);
                } else if (grid[i][j] == 'H') {
                    antHoleRow = i;
                    antHoleCol = j;
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
    public boolean toggleWall(int r, int c) {
        if (r > 10 || r < 0 || c > 10 || c < 0) {
            return false;
        } else {
            if (grid[r][c] == '#') {
                for (int i = 0; i < walls.size(); i++) {
                    if (walls.get(i).getRow() == r && walls.get(i).getCol() == c) {
                        walls.remove(i);
                    }
                }
                grid[r][c] = '.';
            } else if (grid[r][c] == '.') {
                grid[r][c] = '#';
                walls.add(new Wall(r, c));
                return true;

            }
            return false;
        }
    }

    /**
     * Toggles between a wall and a plant space.
     * 
     * @param r row of the location to be toggled
     * @param c column of the location to be toggled
     */
    public boolean togglePlant(int r, int c) {
        if (r > 11 || r < 0 || c > 11 || c < 0) {
            return false;
        } else {
            if (grid[r][c] == 'P') {
                for (int i = 0; i < plants.size(); i++) {
                    if (plants.get(i).getRow() == r && plants.get(i).getCol() == c) {
                        plants.remove(i);
                    }
                }
                grid[r][c] = '#';
                walls.add(new Wall(r,c));
            } else if (grid[r][c] == '#') {
                grid[r][c] = 'P';
                Wall w = new Wall(r, c);
                plants.add(new Plant(w));
                for (int i = 0; i < walls.size(); i++) {
                    if (walls.get(i).getRow() == r && walls.get(i).getCol() == c) {
                        walls.remove(i);
                    }
                }
                return true;

            }
            return false;
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
            walls.add((Wall) type);
            grid[r][c] = '#';
            System.out.println("hi");
        } else if (type instanceof Insect) {
            insects.add((Insect) type);
            grid[r][c] = 'I';
        } else if (type instanceof Plant) {
            plants.add((Plant) type);
            grid[r][c] = 'P';
        }
    }

    /**
     * Returns the object at r, c on the grid.
     * 
     * @param r Row
     * @param c Column
     * @return the object and r, c
     */
    public Object get(int r, int c) {

        if (r > 10 || r < 0 || c > 10 || c < 0) {
            return new Object();
        } else {
            if (grid[r][c] == 'I') {
                for (int i = 0; i < insects.size(); i++) {
                    if (insects.get(i).getCol() == c && insects.get(i).getRow() == r) {
                        return insects.get(i);
                    }
                }
            } else if (grid[r][c] == 'P') {
                for (int p = 0; p < plants.size(); p++) {
                    if (plants.get(p).getCol() == c && plants.get(p).getRow() == r) {
                        return plants.get(p);
                    }
                }
            } else if (grid[r][c] == '#') {
                for (int w = 0; w < walls.size(); w++) {
                    if (walls.get(w).getCol() == c && walls.get(w).getRow() == r) {
                        return walls.get(w);
                    }
                }
            }
        }
        return null;
    }

    class SortByLength implements Comparator<Insect> {
        public int compare(Insect a, Insect b) {
            return a.getPathLen() - b.getPathLen();
        }
    }

    /**
     * Starts and continues to run the program
     */
    public void act(PApplet marker) {

        if (insects != null) {
            for (Insect i : insects) {
                i.findOptimalPath(i.getRow(), i.getCol(), grid);
            }

            ArrayList<Insect> dead = new ArrayList<>();

            insects.sort(new SortByLength());
            for (Insect i : insects) {
                i.act(fruit);
                if (i.getHealth() <= 0) {
                    dead.add(i);
                }
            }

            // Remove dead insects whose health is non-positive.
            insects.removeAll(dead);
        }

        if (plants != null) {
            lines.clear();
            for (Plant p : plants) {
                ArrayList<Float> line = p.act(marker, insects);
                if (line != null) {
                    lines.add(line);
                }
            }
        }

        if (insects != null) {
            ArrayList<Insect> dead = new ArrayList<>();
            for (Insect i : insects) {
                if (i.getHealth() <= 0) {
                    dead.add(i);
                }
            }

            // Remove dead insects whose health is non-positive.
            insects.removeAll(dead);
        }

        if (fruit.getHealth() <= 0) {
            grid[fruit.getRow()][fruit.getCol()] = '.';
            System.out.println("Game Over");
            gameOver = true;
        }

        // add more insects
        if (grid[antHoleRow + 1][antHoleCol] == '.') {
            insects.add(new Insect(antHoleRow + 1, antHoleCol, grid));
            grid[antHoleRow + 1][antHoleCol] = 'I';
        } else if (grid[antHoleRow][antHoleCol + 1] == '.') {
            insects.add(new Insect(antHoleRow, antHoleCol + 1, grid));
            grid[antHoleRow][antHoleCol + 1] = 'I';
        } else if (grid[antHoleRow][antHoleCol - 1] == '.') {
            insects.add(new Insect(antHoleRow, antHoleCol - 1, grid));
            grid[antHoleRow][antHoleCol - 1] = 'I';
        } else if (grid[antHoleRow - 1][antHoleCol] == '.') {
            insects.add(new Insect(antHoleRow - 1, antHoleCol, grid));
            grid[antHoleRow - 1][antHoleCol] = 'I';
        }

        if ((grid[antHoleRow + 1][antHoleCol] == '#' || grid[antHoleRow + 1][antHoleCol] == 'P')
                && (grid[antHoleRow][antHoleCol + 1] == '#' || grid[antHoleRow][antHoleCol + 1] == 'P')
                && (grid[antHoleRow][antHoleCol - 1] == '#' || grid[antHoleRow][antHoleCol - 1] == 'P')
                && (grid[antHoleRow - 1][antHoleCol] == '#' || grid[antHoleRow - 1][antHoleCol] == 'P')) {
            System.out.println("You cannot cover the ant hole");
            remove(antHoleRow + 1, antHoleCol);
            remove(antHoleRow, antHoleCol + 1);
            remove(antHoleRow, antHoleCol - 1);
            remove(antHoleRow - 1, antHoleCol);
        }

    }

    public ArrayList<Insect> getInsects() {
        return insects;
    }

    public ArrayList<Plant> getPlants() {
        return plants;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }
    
    public Fruit getFruit() {
        return fruit;
    }

//    public int getTrueWidth() {
//        int count = 0;
//        for (int c = 0; c < grid2[0].length; c++) {
//            if (grid2[0][c] == 0) {
//                break;
//            }
//            count++;
//        }
//
//        return count;
//    }
//
//    public int getTrueHeight() {
//        int count = 0;
//        for (int r = 0; r < grid2.length; r++) {
//            if (grid2[r][0] == 0) {
//                break;
//            }
//            count++;
//        }
//
//        return count;
//    }
    
    public void draw(PApplet marker, float x, float y, float width, float height) {
        super.draw(marker, x, y, width, height);
        for (ArrayList<Float> line : lines) {
            marker.pushStyle();
            marker.stroke(55,175,255);
            marker.line(line.get(0), line.get(1), line.get(2), line.get(3));
            marker.popStyle();

        }
    }

}
