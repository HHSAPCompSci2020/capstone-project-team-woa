package template;


import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import processing.core.PApplet;

public abstract class GridTemplate {

    /**
     * Add a field to represent the grid. This time, make it a 2D array of characters.
     **/
    protected char[][] grid;

    /**
     * Construct an empty 2D array with some default dimensions.
     */
    public GridTemplate() {
        grid = new char[11][11];
    }

    /**
     * Construct an empty 2D array with dimensions width and height, then fill it with data from the file filename.
     * 
     * @param row    The number of rows on the grid.
     * @param col    The number of columns on the grid.
     * @param filename The text file to read from.
     */
    public GridTemplate(int row, int col, String filename) {
        grid = new char[row][col];
        this.readData(filename, grid);
    }

    /**
     * Code a toString() method that nicely prints the grid to the commandline.
     * 
     */
    public String toString() {
        String output = "";

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                    output += grid[i][j];
            }
            output += "\n";
        }
        
        
        return output;
    }

    /**
     * (Graphical UI) Draws the grid on a PApplet. The specific way the grid is
     * depicted is up to the coder.
     * 
     * @param marker The PApplet used for drawing.
     * @param x      The x pixel coordinate of the upper left corner of the grid drawing.
     * @param y      The y pixel coordinate of the upper left corner of the grid drawing.
     * @param width  The pixel width of the grid drawing.
     * @param height The pixel height of the grid drawing.
     */
    public void draw(PApplet marker, float x, float y, float width, float height) {
        marker.noFill();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                float rectWidth = width / grid[0].length;
                float rectHeight = height / grid.length;
                float rectX = x + j * rectWidth;
                float rectY = y + i * rectHeight;

                marker.text(grid[i][j], rectX + 4, rectY + 13);
                marker.rect(rectX, rectY, rectWidth, rectHeight);
//                if (grid[i][j] == '*' || grid[i][j] == '#') {
//                    marker.fill(110);
//                    marker.rect(rectX, rectY, rectWidth, rectHeight);
//                    marker.noFill();
//                } else if (grid[i][j] == '!') {
//                    marker.fill(60);
//                    marker.rect(rectX, rectY, rectWidth, rectHeight);
//                    marker.noFill();
//                } else if (grid[i][j] == 'X') {
//                    marker.text(grid[i][j], rectX, rectY);
//                    marker.rect(rectX, rectY, rectWidth, rectHeight);
//                } else {
//                    marker.rect(rectX, rectY, rectWidth, rectHeight);
//                }
            }
        }
    }

    /**
     * (Graphical UI) Determines which element of the grid matches with a particular
     * pixel coordinate. This supports interaction with the grid using mouse clicks
     * in the window.
     * 
     * @param p      A Point object containing a graphical pixel coordinate.
     * @param x      The x pixel coordinate of the upper left corner of the grid drawing.
     * @param y      The y pixel coordinate of the upper left corner of the grid drawing.
     * @param width  The pixel width of the grid drawing.
     * @param height The pixel height of the grid drawing.
     * @return A Point object representing a coordinate within the grid, or null if
     *         the pixel coordinate falls completely outside of the grid.
     */
    public Point clickToIndex(Point p, float x, float y, float width, float height) {
        int clickX = p.x;
        int clickY = p.y;

        // math
        double rectWidth = (width / grid[0].length);
        double rectHeight = (height / grid.length);
        int i = (int) (clickY / rectWidth);
        int j = (int) (clickX / rectHeight);

        Point coordinate = new Point(i, j);

        return coordinate;
    }

    public void readData(String filename, char[][] gameData) {
        File dataFile = new File(filename);

        if (dataFile.exists()) {
            int count = 0;

            FileReader reader = null;
            Scanner in = null;
            try {
                reader = new FileReader(dataFile);
                in = new Scanner(reader);

                while (in.hasNext()) {
                    String line = in.nextLine();
                    for (int i = 0; i < line.length(); i++)
                        if (count < gameData.length && i < gameData[count].length)
                            gameData[count][i] = line.charAt(i);

                    count++;
                }

            } catch (IOException ex) {
                throw new IllegalArgumentException("Data file " + filename + " cannot be read.");
            } finally {
                if (in != null)
                    in.close();
            }

        } else {
            throw new IllegalArgumentException("Data file " + filename + " does not exist.");
        }
    }
}
