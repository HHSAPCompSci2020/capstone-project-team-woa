
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
    private Grid grid = new Grid("maps/test1.txt");;
    private int materials, coins;
    private int time;
    private int gameCond;
    private GButton map1;
    private GButton map2;
    private GButton map3;  
    private GButton map4;
    private GButton restart;
    private GButton playAgain;
    private GLabel coinDisplay;
    private GLabel matDisplay;
    private GLabel instructions;
    private ArrayList<Wall> initWalls = grid.getWalls();
    private ArrayList<Plant> initPlants = grid.getPlants();
    private PImage insectImage;
    private PImage wallImage;
    private PImage plantImage;
    private PImage holeImage;
    private PImage fruitImage;

    public DrawingSurface() {

        materials = 5;
        coins = 150;
        time = 0;
        gameCond = 0;
    }
    
    public void setup() {
        coinDisplay = new GLabel(this, 400, 100, 560, 20, "");
        map1 = new GButton(this, 100, 100, 100, 40, "");
        map2 = new GButton(this, 200, 100, 100, 40, "");
        map3 = new GButton(this, 300, 100, 100, 40, "");
        map4 = new GButton(this, 400, 100, 100, 40, "");
        restart = new GButton(this, 400, 60, 100, 40,"");
        playAgain = new GButton(this, 400, 60, 100, 40,"");

        matDisplay = new GLabel(this, 400, 140, 560, 20, "");
        instructions = new GLabel(this, height/4, height/4, 560, 160, "");
        insectImage = loadImage("Ant.png");
        wallImage = loadImage("Wall.png");
        plantImage = loadImage("Plant.png");
        holeImage = loadImage("Hole.png");
        fruitImage = loadImage("Fruit1.png");
    }

    public void draw() {
        background(255);
        fill(0);
        textAlign(LEFT);
        textSize(12);
        stroke(255, 255, 255);

        if (gameCond == 0) {

            map1.setText("Press to Play Map 1");
            map2.setText("Press to Play Map 2");
            map3.setText("Press to Play Map 3");
            map4.setText("Press to Play Map 4");

            restart.setVisible(false);
            restart.setEnabled(false);
            playAgain.setVisible(false);
            playAgain.setEnabled(false);
            instructions.setVisible(true);
            instructions.setText("When you enter, ants will start climbing out of\ntheir hole and try to eat the fruit.\nYour goal is to stop the ants by placing plants and walls.\n Plants damage the ants and walls block the ants.\nPlace or remove plants by right clicking on a location with a wall.\nDo the same for walls but use left click and place on empty locations\nIf the ants eat the fruit you lose.\nYou can still try again, or restart the level\nwhenever you like.");

        } else if (gameCond % 2 == 1) {

            restart.setVisible(true);
            restart.setEnabled(true);
            instructions.setVisible(false);
            map1.setVisible(false);
            map1.setEnabled(false);
            map2.setVisible(false);
            map2.setEnabled(false);
            map3.setVisible(false);
            map3.setEnabled(false);
            map4.setVisible(false);
            map4.setEnabled(false);


            coinDisplay.setText("Coins: " + coins);
            matDisplay.setText("Materials: " + materials);
            time++;
            restart.setText("Restart");

            if (time % 60 == 0 && !grid.gameOver) {
                grid.act(this);
            }
            // pushStyle();
            for (int j = 0; j < grid.getInsects().size(); j++) {
                image(insectImage, height / 11 * .75f * grid.getInsects().get(j).getCol(),
                        height / 11 * .75f * grid.getInsects().get(j).getRow(), height / 11 * .75f, height / 11 * .75f);
            }
            for (int k = 0; k < grid.getPlants().size(); k++) {
                image(plantImage, height / 11 * .75f * grid.getPlants().get(k).getCol(),
                        height / 11 * .75f * grid.getPlants().get(k).getRow(), height / 11 * .75f, height / 11 * .75f);
            }
            for (int k = 0; k < grid.getWalls().size(); k++) {
                image(wallImage, height / 11 * .75f * grid.getWalls().get(k).getCol(),
                        height / 11 * .75f * grid.getWalls().get(k).getRow(), height / 11 * .75f, height / 11 * .75f);
            }
            
            image(holeImage, height / 11 * .75f * grid.antHoleCol,
                    height / 11 * .75f * grid.antHoleRow, height / 11 * .75f, height / 11 * .75f);
            
            image(fruitImage, height / 11 * .75f * grid.getFruit().getCol(),
                    height / 11 * .75f * grid.getFruit().getRow(), height / 11 * .75f, height / 11 * .75f);
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
                for (int j = 0; j < grid.getInsects().size(); j++) {
                    image(insectImage, height / 11 * .75f * grid.getInsects().get(j).getCol(),
                            height / 11 * .75f * grid.getInsects().get(j).getRow(), height / 11 * .75f, height / 11 * .75f);
                }
                pushStyle();
                stroke(0, 0, 0);
                noFill();
                rect(0, 0, height * .75f, height * .75f);
                popStyle();
                


            } else if (grid.gameOver) {
                coinDisplay.setText("");
                matDisplay.setText("");

                this.text("GAME OVER", height*.75f / 2, height*.75f / 2);
                restart.setVisible(false);
                restart.setEnabled(false);
                playAgain.setEnabled(true);
                playAgain.setText("Do you want to play again?");
                playAgain.setVisible(true);

            }

            // Remove based on preference
            
        } else if (gameCond % 2 == 0) {
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
                if (coins >= 10) {
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

        if(button == map1) {
            grid = new Grid("maps/test3.txt");
            initWalls = grid.getWalls();
            initPlants = grid.getPlants();
            gameCond++;
            time = 0;   
        }
        else if(button == map2) {
            grid = new Grid("maps/test4.txt");
            initWalls = grid.getWalls();
            initPlants = grid.getPlants();
            gameCond++;
            time = 0;
        }
        else if(button == map3) {
            grid = new Grid("maps/test5.txt");
            initWalls = grid.getWalls();
            initPlants = grid.getPlants();
            gameCond++;
            time = 0;
        }
        else if(button == map4) {
            grid = new Grid("maps/test6.txt");
            initWalls = grid.getWalls();
            initPlants = grid.getPlants();
            gameCond++;
            time = 0;
        }
        else if(button == restart) {
            grid = new Grid(grid.file);
            gameCond++;
            time = 0;
        }
        else if(button == playAgain) {
            gameCond=0;
            time = 0;
            coins = 15;
            materials = 5;
            map1.setVisible(true);
            map1.setEnabled(true);
            map2.setVisible(true);
            map2.setEnabled(true);
            map3.setVisible(true);
            map3.setEnabled(true);
            map4.setVisible(true);
            map4.setEnabled(true);
            
        }
       
        
        
        
        
        
        
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
