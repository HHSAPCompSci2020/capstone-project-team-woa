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
     * @param wall The home location of the plant
     * 
     */
    public Plant(Wall wall) {
        home = wall;
        range = 1;
        damageDealt = 10;

    }

    /**
     * Shoots at ants that are in the range of this plant and deals damageDealt
     * damage
     * 
     * @param marker  The PApplet that draws the beam
     * @param insects The insects that are in the grid
     */
    public void shoot(PApplet marker, ArrayList<Insect> insects) {
        // Find neighboring insects
        ArrayList<Insect> neighbors = new ArrayList<Insect>();

        for (int r = getRow() - range; r < getRow() + range + 1; r++) {
            for (int c = getCol() - range; c < getCol() + range + 1; c++) {
                if (returnInsect(r, c, insects) != null) {
                    neighbors.add(returnInsect(r, c, insects));
                }

            }
        }

        if (neighbors.size() != 0)

        {
            // Calculate the index of a random insect and calculate its location
            int randIndex = (int)( Math.random() * neighbors.size());

            // makes sure the insect does really exist
            while (neighbors.get(randIndex) == null) {
                randIndex = (int) Math.random() * neighbors.size();
            }
            int insectRow = neighbors.get(randIndex).getRow();
            int insectCol = neighbors.get(randIndex).getCol();

            // Draw a line from the plant to the insect

            marker.line(getRow(), getCol(), insectRow, insectCol);

           
            // Does damage to the insect
            neighbors.get(randIndex).takeDamage(damageDealt);

        }

    }

    /**
     * The plant continues to act, which includes shooting insects that get too
     * close
     * 
     * @param m       The PApllet used to draw the beam;
     * @param insects The insects that are in the grid
     */
    public void act(PApplet m, ArrayList<Insect> insects) {
        if (hasNeighbor(insects)) {

            shoot(m, insects);
        }
    }

    /**
     * Checks if there is at least one insect in the plant's range
     * 
     * @param insects The insects that are in the grid
     * @return Returns true if there is at least one insect in range, false
     *         otherwise
     */
    public boolean hasNeighbor(ArrayList<Insect> insects) {
        if (insects.size() == 0) {
            return false;
        }
        ArrayList<Insect> neighbors = new ArrayList<Insect>();
        for (int r = getRow() - range; r < getRow() + range + 1; r++) {
            for (int c = getCol() - range; c < getCol() + range + 1; c++) {
                if (r != getRow() && c != getCol())
                    neighbors.add(returnInsect(r, c, insects));

            }
        }
        if (neighbors.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Levels up which will increase damageDealt and range
     */
    public void levelUp() {
        level++;
        if (level < 4) {
            range++;
        }

    }

    /**
     * Returns the range of this plant
     * 
     * @return the range of this plant
     */
    public int getRange() {
        return range;
    }

    /**
     * Returns the row of this plant in the grid
     * 
     * @return the row of this plant
     */
    public int getRow() {
        return home.getRow();
    }

    /**
     * Returns the column of this plant in the grid
     * 
     * @return the column of this plant
     */
    public int getCol() {
        return home.getCol();
    }

    /**
     * Returns the insect given its row and column
     * 
     * @param row     Row of desired insect
     * @param col     Column of desired insect
     * @param insects The insects that are in the grid
     * @return an insect at with specified location if it exists, else it returns
     *         null
     */
    public Insect returnInsect(int row, int col, ArrayList<Insect> insects) {

        // Checks every insect in the grid to see if they are at (row,col)

        for (int i = 0; i < insects.size(); i++) {
            if (insects.get(i).getRow() == row && insects.get(i).getCol() == col) {
                // return this insect if it is the correct one
                return insects.get(i);
            }
        }

        return null;
    }

}
