package actors;
import java.util.ArrayList;

import grid.Grid;
import processing.core.PApplet;

/**
 * A plant that attacks any insect that is nearby. As it levels up, damage and
 * range increase
 * 
 * @author Dhruv Masurekar
 */

public class Plant {

    protected int damageDealt;
    protected int range;
    protected Wall home;
    protected int level;

    /**
     * Initializes the Plant by giving a value to the field
     * 
     */
    public Plant(Wall wall) {
        home = wall;
        range = 1;
        damageDealt = 1;

    }

    /**
     * Shoots at ants that are in the range of this plant and deals damageDealt
     * damage
     */
    public void shoot(PApplet marker, ArrayList<Insect> insects) {
        
    }

    /**
     * The plant continues to act, which includes shooting insects that get too
     * close
     */
    public void act() {

        

    }

    /**
     * Levels up which will increase damageDealt and range
     */
    public void levelUp() {
        level++;

    }

    public int getRange() {

        return range;
    }

    public int getX() {
        return home.getX();
    }
    public int getY() {
        return home.getY();
    }
}
