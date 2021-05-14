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

    private ArrayList<Point> optimalPath;
    private int row, col;
    private char[][] grid;
    private int health;

    /**
     * 
     * @param r row of the Insect
     * @param c column of the Insect
     * @param grid grid
     */
    public Insect(int r, int c, char[][] grid) {
        this.row = r;
        this.col = c;
        this.grid = grid;
        health = 10;
    }

    /**
     * Finds and returns the shortest path from the input location to the fruit.
     * 
     * @param r row of the location
     * @param c column of the location
     * @param grid grid
     * @return returns the points which the insect must take to get to the fruit
     *         fastest
     */
    public ArrayList<Point> findOptimalPath(int r, int c, char[][] grid) {
        optimalPath = findNext(r, c, grid);
        return findNext(r, c, grid);
    }

    /**
     * 
     * @return returns the row of the insect
     */
    public int getRow() {
        return row;
    }
    
    /**
     * 
     * @return returns the column of the insect
     */
    public int getCol() {
        return col;
    }
    
    /**
     * Makes the insect take a step forward if it isn't blocked from the fruit. 
     * Does nothing if it is blocked from the fruit. 
     */
    public void act() {
        if (optimalPath != null) {
            System.out.println(optimalPath.toString());
            Point p = optimalPath.get(1);
            row = p.x;
            col = p.y;
            grid[row][col] = 'I';
        }
    }
    
    /**
     * 
     * @param dmg the damage given to the insect.
     */
    public void takeDamage(int dmg) {
        health -= dmg;
    }

    private ArrayList<Point> findNext(int x, int y, char[][] grid) {

        if (!isValid(x, y, grid) || grid[x][y] == '!' || grid[x][y] == '#' || grid[x][y] == 'A' || grid[x][y] == '@') {
            return null;
        } else if (grid[x][y] == 'X') {
            ArrayList<Point> list = new ArrayList<>();
            return list;
        } else if (grid[x][y] == '.' || grid[x][y] == 'I') {
            ArrayList<ArrayList<Point>> result = new ArrayList<>();
            grid[x][y] = '!';
            if (isValid(x + 1, y, grid))
                result.add(0, findNext(x + 1, y, grid));
            if (isValid(x - 1, y, grid)) {
                result.add(0, findNext(x - 1, y, grid));
            }
            if (isValid(x, y + 1, grid))
                result.add(0, findNext(x, y + 1, grid));
            if (isValid(x, y - 1, grid)) {
                result.add(0, findNext(x, y - 1, grid));
            }

            int minSize = Integer.MAX_VALUE;
            int minPathIndex = -1;
            for (int i = 0; i < result.size(); i++) {
                if (result.get(i) != null && result.get(i).size() < minSize) {
                    minSize = result.get(i).size();
                    minPathIndex = i;
                }
            }
            grid[x][y] = '.';
            if (minPathIndex == -1) {
                return null;
            }

            ArrayList<Point> m = result.get(minPathIndex);
            m.add(0, new Point(x, y));
            return m;
        } else {
            System.out.println("Unsupported char");
            return null;
        }
    }

    private boolean isValid(int x, int y, char[][] grid) {
        return (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length);
    }
}
