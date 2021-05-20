package actors;

/**
 * A fruit that is at the end of the maze created by the player The insects try
 * to get to the fruit and if they do and eat it completely, the game ends
 * 
 * @author Dhruv Masurekar
 */
public class Fruit {

    private int health, row, col;

    /**
     * Creates a fruit with 100 health and initializes coordinates
     * 
     * @param health Starting health of the fruit
     * @param r      Row location of the fruit
     * @param c      Column location of the fruit
     */
    public Fruit(int health, int r, int c) {
        this.health = health;
        row = r;
        col = c;

    }

    /**
     * Decreases the health of the fruit and once health reaches 0, the game ends.
     * 
     * @param damage Damage the fruit takes
     */
    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            die();
        }
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public int getHealth() {
        return health;
    }

    /**
     * Kills the plant and removes it from the grid
     */
    public void die() {

    }
}
