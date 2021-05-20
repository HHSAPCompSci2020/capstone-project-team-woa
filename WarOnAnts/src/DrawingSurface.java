
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import actors.Wall;
import grid.Grid;
import template.GridTemplate;
import processing.core.PApplet;

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
        }

    }

    public void mousePressed() {
        if (mouseButton == LEFT) {
            Point click = new Point(mouseX, mouseY);
            float dimension = height;
            Point cellCoord = grid.clickToIndex(click, 0, 0, dimension, dimension);
            if (cellCoord != null) {
                grid.toggleWall(cellCoord.x, cellCoord.y);
            }
        }
        if (mouseButton == RIGHT) {
            grid.act();
        }
    }

}
