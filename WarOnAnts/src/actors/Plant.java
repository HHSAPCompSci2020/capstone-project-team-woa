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
    public void shoot(PApplet marker, Grid g) {

        // Find neighboring insects
        ArrayList<Insect> neighbors = new ArrayList<Insect>();
        for (int r = getY() - range; r < getY() + range + 1; r++) {
            for (int c = getX() - range; c < getX() + range + 1; c++) {
                if (r != getY() && c != getX())
                    neighbors.add(g.returnInsect(r, c));

            }
        }
        // Calculate the index of a random insect and calculate its location
        int randIndex = (int) Math.random() * neighbors.size();

        //makes sure the insect does really exist
        while(neighbors.get(randIndex)==null) {
            randIndex = (int) Math.random() * neighbors.size();

        }
        int insectX = neighbors.get(randIndex).getX();
        int insectY = neighbors.get(randIndex).getY();

        // Draw a line from the plant to the insect
        marker.line(getX(), getY(), insectX, insectY);
        
        // Kill the insect by removing it from grid; 
        g.remove(insectX, insectY);

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
