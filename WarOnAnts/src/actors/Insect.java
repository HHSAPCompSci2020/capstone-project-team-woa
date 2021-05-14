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

    /**
     * Creates an insect and initializes the initial coordinates
     * 
     */
    public Insect(int r, int c, char[][] grid) {
        this.row = r;
        this.col = c;
        this.grid = grid;
    }

    /**
     * Finds and returns the shortest path from the input location to the fruit.
     * 
     * @param x X coordinate of the location
     * @param y Y coordinate of the location
     * @return returns the points which the insect must take to get to the fruit
     *         fastest
     */
    public ArrayList<Point> findOptimalPath(int r, int c, char[][] grid) {
        optimalPath = findNext(r, c, grid);
        return findNext(r, c, grid);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    
    public void act() {
        System.out.println(optimalPath.toString());
        Point p = optimalPath.get(1);
        row = p.x;
        col = p.y;
        grid[row][col] = 'I';
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
