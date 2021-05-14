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
     * @param marker The PApplet that draws the beam
     * @param g The grid that houses the ants
     */
    public void shoot(PApplet marker, Grid g) {

        // Find neighboring insects
        ArrayList<Insect> neighbors = new ArrayList<Insect>();
        for (int r = getRow() - range; r < getRow() + range + 1; r++) {
            for (int c = getCol() - range; c < getCol() + range + 1; c++) {
                if (r != getRow() && c != getCol())
                    neighbors.add(g.returnInsect(r, c));

            }
        }
        // Calculate the index of a random insect and calculate its location
        int randIndex = (int) Math.random() * neighbors.size();

        //makes sure the insect does really exist
        while(neighbors.get(randIndex)==null) {
            randIndex = (int) Math.random() * neighbors.size();

        }
        int insectRow = neighbors.get(randIndex).getRow();
        int insectCol = neighbors.get(randIndex).getCol();

        // Draw a line from the plant to the insect
        marker.line(getRow(), getCol(), insectRow, insectCol);
        
        // Kill the insect by removing it from grid; 
        g.remove(insectRow, insectCol);

    }

    /**
     * The plant continues to act, which includes shooting insects that get too
     * close
     */
    public void act(PApplet m, Grid g) {
       if(hasNeighbor(g)) {
           shoot(m,g);
       }
    }
    /**
     * Checks if there is at least one insect in the plant's range
     * @param g The grid that houses all the insects
     * @return Returns true if there is at least one insect in range, false otherwise
     */
    private boolean hasNeighbor(Grid g) {
        ArrayList<Insect> neighbors = new ArrayList<Insect>();
        for (int r = getRow() - range; r < getRow() + range + 1; r++) {
            for (int c = getCol() - range; c < getCol() + range + 1; c++) {
                if (r != getRow() && c != getCol())
                    neighbors.add(g.returnInsect(r, c));

            }
        }
        if(neighbors.size()>0) {
            return true;
        }
        return false;
    }

    /**
     * Levels up which will increase damageDealt and range
     */
    public void levelUp() {
        level++;

    }
    /**
     * Returns the range of this plant
     * @return the range of this plant
     */
    public int getRange() {
        return range;
    }
    /**
     * Returns the row of this plant in the grid
     * @return the row of this plant
     */
    public int getRow() {
        return home.getRow();
    }
    /**
     * Returns the column of this plant in the grid
     * @return the column of this plant
     */
    public int getCol() {
        return home.getCol();
    }
}
