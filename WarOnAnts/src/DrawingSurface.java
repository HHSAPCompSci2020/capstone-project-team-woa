
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import actors.*;
import g4p_controls.GAlign;
import g4p_controls.GButton;
import g4p_controls.GEvent;
import g4p_controls.GLabel;
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
    private boolean startGame;
    private GButton start;
    private boolean repeat;

    public DrawingSurface() {

        grid = new Grid("maps/test4.txt");
        System.out.println(grid);
        materials = 5;
        coins = 15;
        time = 0;
        startGame = true;
        repeat = true;     
    }

    public void draw() {
        background(255);
        fill(0);
        textAlign(LEFT);
        textSize(12);
        
        if (startGame) {
            start = new GButton(this, 100, 60, 100, 40, "Press to Play"); 
            
        } 
        else if(!startGame) {
            time++;
            if (time % 20 == 0 && !grid.gameOver) {
                grid.act(this);
            }

            if (grid != null && !grid.gameOver) {

                grid.draw(this, 0, 0, height, height);
            } else if (grid.gameOver) {
                startGame = true;
                this.text("GAME OVER", 100, 100);
                start = new GButton(this, 100, 120, 100, 40, "Play again?");
                
                
            }

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
                    } else {
                        grid.toggleWall(cellCoord.x, cellCoord.y);
                    }
                }
            }
        }

        // toggle between plant and wall
        if (mouseButton == RIGHT) {
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
                    } else {
                        grid.togglePlant(cellCoord.x, cellCoord.y);
                    }
                }
            }
        }

    }

    public void handleButtonEvents(GButton button, GEvent event) {
          
        startGame = false; 
       
         

    }
}
