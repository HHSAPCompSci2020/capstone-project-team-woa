package actors;
import java.util.ArrayList;

import processing.core.PApplet;

/**
 * A version of a plant that shoots a beam of energy at any insect that is
 * nearby. As it levels up, damage and range increase
 * 
 * @author Dhruv Masurekar
 */

public class EnergyPlant extends Plant {

    /**
     * Initializes the EnergyPlant by giving a value to the field
     * 
     */
    public EnergyPlant(Wall home) {
        super(home);
    }

    /**
     * Shoots at ants that are in the range of this plant and deals damageDealt
     * damage to the insect
     */
    @Override
    public void shoot(PApplet marker, ArrayList<Insect> insects) {
        super.shoot(marker, insects);

    }

}
