
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
    private Grid grid = new Grid("maps/test4.txt");
    private int materials, coins;
    private int time;
    private int gameCond;
    private GButton start;
    private GLabel coinDisplay;
    private GLabel matDisplay;
    private ArrayList<Wall> initWalls = grid.getWalls();
    private ArrayList<Plant> initPlants = grid.getPlants();
    private PImage insectImage;
    private PImage wallImage;
    private PImage plantImage;

    public DrawingSurface() {

        grid = new Grid("maps/test4.txt");
        System.out.println(grid);
        materials = 5;
        coins = 15;
        time = 0;
        gameCond = 0;
    }

    public void setup() {
        coinDisplay = new GLabel(this, 300, 100, 560, 20, "");
        start = new GButton(this, 300, 60, 100, 40, "Press to Play");
        matDisplay = new GLabel(this, 300, 140, 560, 20, "");
        insectImage = loadImage("Ant.png");
        wallImage = loadImage("Wall.png");
        plantImage = loadImage("Plant.png");
    }

    public void draw() {
        background(255);
        fill(0);
        textAlign(LEFT);
        textSize(12);
        stroke(255, 255, 255);

        if (gameCond == 0) {

            start.setText("Press to Play");

        } else if (gameCond % 2 == 1) {

            coinDisplay.setText("Coins: " + coins);
            matDisplay.setText("Materials: " + materials);
            time++;
            start.setText("Restart");

            if (time % 30 == 0 && !grid.gameOver) {
                grid.act(this);
            }
            // pushStyle();
            for (int j = 0; j < grid.getInsects().size(); j++) {
                image(insectImage, height / 11 * .75f * grid.getInsects().get(j).getCol(),
                        height / 11 * .75f * grid.getInsects().get(j).getRow(), 378f / 11 * .75f, 378f / 11 * .75f);
            }
            for (int k = 0; k < grid.getPlants().size(); k++) {
                image(plantImage, height / 11 * .75f * grid.getPlants().get(k).getCol(),
                        height / 11 * .75f * grid.getPlants().get(k).getRow(), 378f / 11 * .75f, 378f / 11 * .75f);
            }
            for (int k = 0; k < grid.getWalls().size(); k++) {
                image(wallImage, height / 11 * .75f * grid.getWalls().get(k).getCol(),
                        height / 11 * .75f * grid.getWalls().get(k).getRow(), 378f / 11 * .75f, 378f / 11 * .75f);
            }
            // popStyle();

            if (grid != null && !grid.gameOver) {
                
//                for (ArrayList<Float> line : lines) {
//                    marker.pushStyle();
//                    marker.stroke(55,175,255);
//                    marker.line(line.get(0), line.get(1), line.get(2), line.get(3));
//                    marker.popStyle();
//
//                }
                
                grid.draw(this, 0, 0, height * .75f, height * .75f);
               
                pushStyle();
                stroke(0, 0, 0);
                noFill();
                rect(0, 0, height * .75f, height * .75f);
                popStyle();
                


            } else if (grid.gameOver) {
                coinDisplay.setText("");
                matDisplay.setText("");

                this.text("GAME OVER", 100, 100);
                start.setText("Play again?");

            }

            // Remove based on preference
            
        } else if (gameCond % 2 == 0) {
            grid = new Grid("maps/test4.txt");
            coins = 15;
            materials = 5;
            gameCond++;
        }

    }

    public void mousePressed() {
        Point click = new Point(mouseX, mouseY);
        float dimension = height * .75f;
        Point cellCoord = grid.clickToIndex(click, 0, 0, dimension, dimension);

        // toggle between wall and path
        if (mouseButton == LEFT) {
            if (cellCoord != null && materials >= 0) {

                if (materials != 0) {
                    if (grid.toggleWall(cellCoord.x, cellCoord.y)) {
                        materials--;

                    } else {
                        grid.toggleWall(cellCoord.x, cellCoord.y);
                        if (grid.get(cellCoord.x, cellCoord.y) instanceof Wall) {
                            if (!hasWall(initWalls, (Wall) grid.get(cellCoord.x, cellCoord.y))) {
                                if (materials < 5) {
                                    materials++;
                                }
                                grid.toggleWall(cellCoord.x, cellCoord.y);
                            }
                        }

                    }

                }

                else {
                    if (grid.get(cellCoord.x, cellCoord.y) instanceof Wall) {
                        if (!hasWall(initWalls, (Wall) grid.get(cellCoord.x, cellCoord.y))) {
                            if (!grid.toggleWall(cellCoord.x, cellCoord.y)) {
                                materials++;
                            } else {
                                grid.toggleWall(cellCoord.x, cellCoord.y);
                            }
                        }

                    }
                }
            }

        }

        // toggle between plant and wall
        if (mouseButton == RIGHT)

        {
            if (cellCoord != null && coins >= 0) {
                if (coins != 0) {
                    if (grid.togglePlant(cellCoord.x, cellCoord.y)) {
                        coins -= 10;

                    } else {
                        grid.togglePlant(cellCoord.x, cellCoord.y);
                        if (grid.get(cellCoord.x, cellCoord.y) instanceof Plant) {
                            if (!hasPlant(initPlants, (Plant) grid.get(cellCoord.x, cellCoord.y))) {
                                if (coins < 200) {
                                    coins += 5;
                                }
                                grid.togglePlant(cellCoord.x, cellCoord.y);
                            }
                        }

                    }

                }

                else {
                    if (grid.get(cellCoord.x, cellCoord.y) instanceof Plant) {
                        if (!hasPlant(initPlants, (Plant) grid.get(cellCoord.x, cellCoord.y))) {
                            if (!grid.togglePlant(cellCoord.x, cellCoord.y)) {
                                coins += 5;
                            } else {
                                grid.togglePlant(cellCoord.x, cellCoord.y);
                            }
                        }

                    }
                }
            }
        }

    }

    public void handleButtonEvents(GButton button, GEvent event) {

        gameCond++;
        time = 0;
    }

    public boolean hasPlant(ArrayList<Plant> list, Plant o) {
        for (Plant p : list) {
            if (p.getCol() == o.getCol() && p.getRow() == o.getRow()) {
                return true;
            }
        }

        return false;
    }

    public boolean hasWall(ArrayList<Wall> list, Wall o) {
        for (Wall w : list) {
            if (w.getCol() == o.getCol() && w.getRow() == o.getRow()) {
                return true;
            }
        }

        return false;
    }
}
