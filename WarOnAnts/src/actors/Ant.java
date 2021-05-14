package actors;
import java.awt.*;
import java.util.ArrayList;
/**
 * An ant who starts from the beginning of the maze and through it to the end.
 * When the ant finishes the maze and eats the plant, the game ends
 * @author William Hyun
 * */
public class Ant extends Insect {

    public Ant(int r, int c, char[][] grid) {
        super(r, c, grid);
    }
}
