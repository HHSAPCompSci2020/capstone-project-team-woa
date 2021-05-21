
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
 * Creates the game that has a grid and in the grid there are plants, ants,
 * walls and a fruit . The ants move toward the fruit, the plants attack the
 * ants and the wall acts like a blockade
 * 
 * @author William Hyun and co-author Dhruv Masurekar
 */
public class DrawingSurface extends PApplet {

    // When you progress to a new prompt, modify this field.
    private Grid grid;
    private int materials, coins;
    private int time;

    public DrawingSurface() {

        grid = new Grid("maps/test4.txt");
        System.out.println(grid);
        materials = 5;
        coins = 15;
        time=0;
    }

    public void draw() {
        background(255);
        fill(0);
        textAlign(LEFT);
        textSize(12);
        time++;
        if(time%20 == 0 && !grid.gameOver) {    
        grid.act(this);
        }
        
        if (grid != null && !grid.gameOver) {

            grid.draw(this, 0, 0, height, height);
        } else if (grid.gameOver) {
            this.text("GAME OVER", 100, 100);;
        }

    }

    public void mousePressed() {
        Point click = new Point(mouseX, mouseY);
        float dimension = height;
        Point cellCoord = grid.clickToIndex(click, 0, 0, dimension, dimension);

        // toggle between wall and path
        if (mouseButton == LEFT) {
            if (cellCoord != null && materials >= 0) {
                if (materials != 0) {
                    if (grid.toggleWall(cellCoord.x, cellCoord.y)) {
                        materials--;

                    } else {
                        materials++;
                    }
                } else {
                    if (!grid.toggleWall(cellCoord.x, cellCoord.y)) {
                        materials++;
                    }
                    else {
                        grid.toggleWall(cellCoord.x, cellCoord.y);
                    }
                }
            }
        }

        // toggle between plant and wall
        if (mouseButton == 3) {
            if (cellCoord != null && coins >= 0) {
                if (coins != 0) {
                    if (grid.togglePlant(cellCoord.x, cellCoord.y)) {
                        coins--;

                    } else {
                        coins--;
                    }
                } else {
                    if (!grid.togglePlant(cellCoord.x, cellCoord.y)) {
                        materials++;
                    }
                    else {
                        grid.togglePlant(cellCoord.x, cellCoord.y);
                    }
                }
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
