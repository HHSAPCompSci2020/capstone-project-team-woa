
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
    private Grid grid = new Grid("maps/test4.txt");
    private int materials, coins, health;
    private int time;
    private int gameCond;
    private GButton start;
    private GLabel coinDisplay;
    private GLabel matDisplay;
    private GLabel healthDisplay;
    private GLabel instructions;
    private ArrayList<Wall> initWalls = grid.getWalls();
    private ArrayList<Plant> initPlants = grid.getPlants();
    private PImage insectImage;
    private PImage wallImage;
    private PImage plantImage;
    private PImage holeImage;
    private PImage fruitImage;

    /**
     * This is the graphical element that is being drawn on screen.
     */
    public DrawingSurface() {
        grid = new Grid("maps/test4.txt");
        System.out.println(grid);
        materials = 5;
        coins = 15;
        time = 0;
        gameCond = 0;
    }

    /**
     * The setup method for the graphical element.
     */
    public void setup() {
        coinDisplay = new GLabel(this, 400, 100, 560, 20, "");
        start = new GButton(this, 400, 60, 100, 40, "Press to Play");
        matDisplay = new GLabel(this, 400, 140, 560, 20, "");
        healthDisplay = new GLabel(this, 400, 180, 560, 20, "");
        instructions = new GLabel(this, height/4, height/4, 560, 160, "");
        instructions = new GLabel(this, height / 10, height / 10, 560, 160, "");
        insectImage = loadImage("Ant.png");
        wallImage = loadImage("Wall.png");
        plantImage = loadImage("Plant.png");
        holeImage = loadImage("Hole.png");
        fruitImage = loadImage("Fruit1.png");

    }

    /**
     * Draws the current state of the DrawingSurface. Prints instructions, coin
     * count, material count, etc.
     */
    public void draw() {
        background(255);
        fill(0);
        textAlign(LEFT);
        textSize(12);
        stroke(255, 255, 255);

        if (gameCond == 0) {

            start.setText("Press to Play");
            instructions.setText(
                    "When you enter, ants will start climbing out of\ntheir hole and try to eat the fruit.\nYour goal is to stop the ants by placing plants and walls.\n Plants damage the ants and walls block the ants.\nPlace or remove plants by right clicking on a location with a wall.\nDo the same for walls but use left click and place on empty locations\nIf the ants eat the fruit you lose.\nYou can still try again, or restart the level\nwhenever you like.");

        } else if (gameCond % 2 == 1) {


            health = grid.getFruit().getHealth();

            instructions.setText("");
            coinDisplay.setText("Coins: " + coins);
            matDisplay.setText("Materials: " + materials);
            healthDisplay.setText("Health remaining: " + health);

            time++;
            start.setText("Restart");

            if (time % 30 == 0 && !grid.gameOver) {
                coins += grid.act(this);
            }
            // pushStyle();
            for (int j = 0; j < grid.getInsects().size(); j++) {
                image(insectImage, height / 11 * .75f * grid.getInsects().get(j).getCol(),
                        height / 11 * .75f * grid.getInsects().get(j).getRow(), (height + 5) / 11 * .75f,
                        (height + 5) / 11 * .75f);
            }
            for (int k = 0; k < grid.getPlants().size(); k++) {
                image(plantImage, height / 11 * .75f * grid.getPlants().get(k).getCol(),
                        height / 11 * .75f * grid.getPlants().get(k).getRow(), (height + 5) / 11 * .75f,
                        (height + 5) / 11 * .75f);
            }
            for (int k = 0; k < grid.getWalls().size(); k++) {
                image(wallImage, height / 11 * .75f * grid.getWalls().get(k).getCol(),
                        height / 11 * .75f * grid.getWalls().get(k).getRow(), (height + 5) / 11 * .75f,
                        (height + 5) / 11 * .75f);
            }

            image(holeImage, height / 11 * .75f * grid.antHoleCol, height / 11 * .75f * grid.antHoleRow,
                    height / 11 * .75f, height / 11 * .75f);

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
                            height / 11 * .75f * grid.getInsects().get(j).getRow(), height / 11 * .75f,
                            height / 11 * .75f);
                }
                pushStyle();
                stroke(0, 0, 0);
                noFill();
                rect(0, 0, height * .75f, height * .75f);
                popStyle();

            } else if (grid.gameOver) {
                coinDisplay.setText("");
                matDisplay.setText("");
                this.text("GAME OVER", height / 2, height / 2);
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

    /**
     * Mutator method for the private field coins.
     */
    public void addCoin() {
        coins++;
    }

    /**
     * Handles the mouse inputs.
     */
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

    /**
     * Handles when the player clicks on a button.
     * @param button The GButton that is being interacted with.
     * @param event The interaction.
     */
    public void handleButtonEvents(GButton button, GEvent event) {

        gameCond++;
        time = 0;
    }

    /**
     * @param list List of plants
     * @param o The plant the user is looking for.
     * @return Returns whether or not plant `o` is in the list of plants `list`.
     */
    public boolean hasPlant(ArrayList<Plant> list, Plant o) {
        for (Plant p : list) {
            if (p.getCol() == o.getCol() && p.getRow() == o.getRow()) {
                return true;
            }
        }

        return false;
    }

    /**
     * @param list List of walls.
     * @param o The wall the user is looking for.
     * @return Returns whether or not wall `o` is in the list of walls `list`.
     */
    public boolean hasWall(ArrayList<Wall> list, Wall o) {
        for (Wall w : list) {
            if (w.getCol() == o.getCol() && w.getRow() == o.getRow()) {
                return true;
            }
        }

        return false;
    }
}
