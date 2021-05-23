package actors;

import java.util.ArrayList;

import grid.Grid;
import processing.core.PApplet;

/**
 * A plant that attacks any insect that is nearby. As it levels up, damage and
 * range increase
 * 
 * @author Dhruv Masurekar co-author William Hyun
 */

public class Plant {

    protected int damageDealt;
    protected int range;
    protected Wall home;
    protected int level;
    protected float blockWidth;

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
        blockWidth = 540 / 11 * .75f;

    }

    /**
     * Shoots at ants that are in the range of this plant and deals damageDealt
     * damage
     * 
     * @param marker  The PApplet that draws the beam
     * @param insects The insects that are in the grid
     */
    public ArrayList<Float> shoot(PApplet marker, Insect insect) {
        if (insect != null) {

            // Draw a line from the plant to the insect
//            marker.line(getCol() * blockWidth + blockWidth / 4, getRow() * blockWidth + blockWidth / 4,
//                    insectCol * blockWidth + blockWidth / 4, insectRow * blockWidth + blockWidth / 4);

            // Does damage to the insect
     //       neighbors.get(randIndex).takeDamage(damageDealt);

            ArrayList<Float> line = new ArrayList<>();
            line.add(getCol() * blockWidth + blockWidth / 4);
            line.add(getRow() * blockWidth + blockWidth / 4);
            line.add(insect.getCol() * blockWidth + blockWidth / 4);
            line.add(insect.getRow() * blockWidth + blockWidth / 4);

            return line;
        }
        return null;
    }

    /**
     * The plant continues to act, which includes shooting insects that get too
     * close
     * 
     * @param m       The PApllet used to draw the beam
     * @param insects The insects that are in the grid
     * @return        The list of lines to be drawn
     */
    public ArrayList<Float> act(PApplet m, Insect insect) {
        return (shoot(m, insect));
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
    
    public int getDamage() {
        return damageDealt;
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
