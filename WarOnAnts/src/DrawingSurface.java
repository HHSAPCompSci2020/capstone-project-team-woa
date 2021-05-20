
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import actors.*;
import grid.Grid;
import template.GridTemplate;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Creates the game that has a grid and in the grid there are plants, ants, walls and a fruit .
 * The ants move toward the fruit, the plants attack the ants and the wall acts like a blockade
 * 
 * @author William Hyun and co-author Dhruv Masurekar
 */
public class DrawingSurface extends PApplet {

    // When you progress to a new prompt, modify this field.
    private Grid grid;
    private int materials;

    public DrawingSurface() {

        grid = new Grid("maps/test4.txt");
        System.out.println(grid);
        materials = 20;
    }

    public void draw() {
        background(255);
        fill(0);
        textAlign(LEFT);
        textSize(12);

        if (grid != null) {
            grid.draw(this, 0, 0, height * 1.5f, height * 1.5f);
        }

    }

    public void mousePressed() {
        Point click = new Point(mouseX, mouseY);
        float dimension = height * 1.5f;
        Point cellCoord = grid.clickToIndex(click, 0, 0, dimension, dimension);

        // toggle between wall and path
        if (mouseButton == LEFT) {
            if (cellCoord != null && materials>0) {
                grid.toggleWall(cellCoord.x, cellCoord.y);
                materials--;
            }
        }

        // toggle between plant and wall
        if (mouseButton == 3) {
            if (cellCoord != null) {
                grid.togglePlant(cellCoord.x, cellCoord.y);
            }
        }

        // act
        if (mouseButton == RIGHT) {
            if (cellCoord != null) {
                grid.act(this);

            }
        }
    }

}
