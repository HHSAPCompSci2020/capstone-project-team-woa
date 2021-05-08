import java.util.ArrayList;

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
        if (insects != null) {
            int randIndex = (int) Math.random() * insects.size();

            int insectX = 0;
            int insectY = 0;

//          insectX = insects.get(randIndex).getX();
//          insectY = insects.get(randIndex).getY();

            marker.line(insectX, insectY, home.getX(), home.getY());
            
//          insects.get(randIndex).removeFromGrid();
        }
    }

    /**
     * The plant continues to act, which includes shooting insects that get too
     * close
     */
    public void act() {
        
        ArrayList<Insect> enemies = getSurroundingEnemies();
        
    }

    private ArrayList<Insect> getSurroundingEnemies() {
        
        //return getGrid().getNeighbors(home.getX(), home.getY());
        return null;
    }
    

    /**
     * Levels up which will increase damageDealt and range
     */
    public void levelUp() {

    }
   
    
    public Grid getGrid() {
        return null;
    }
}
