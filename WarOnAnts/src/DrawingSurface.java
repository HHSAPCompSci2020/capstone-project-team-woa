
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

public class DrawingSurface extends PApplet {

    // When you progress to a new prompt, modify this field.
    private Grid grid;

    public DrawingSurface() {

        grid = new Grid("maps/test4.txt");
        System.out.println(grid);
    }

    public void draw() {
        background(255);
        fill(0);
        textAlign(LEFT);
        textSize(12);

        if (grid != null) {

            grid.draw(this, 0, 0, height, height);
            if (grid != null) {
                grid.draw(this, 0, 0, height, height);
            }
        }

    }

    public void mousePressed() {
        Point click = new Point(mouseX, mouseY);
        float dimension = height;
        Point cellCoord = grid.clickToIndex(click, 0, 0, dimension, dimension);

        // toggle between wall and path
        if (mouseButton == LEFT) {
            if (cellCoord != null) {
                grid.toggleWall(cellCoord.x, cellCoord.y);
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
