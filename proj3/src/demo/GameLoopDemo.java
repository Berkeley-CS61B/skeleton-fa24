package demo;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

/**
 * This demo shows how to build an interactive game.
 * In this demo, we create a world with 5 squares.
 * Pressing keys 1 2 3 4 5 causes the corresponding square to change between FLOOR and TILE.
 * Pressing key q causes the game to quit.
 */
public class GameLoopDemo {
    private static final int WIDTH = 5;
    private static final int HEIGHT = 1;

    /**
     * Helper function.
     * If the tile at position (x,y) is FLOOR, change it to WALL.
     * If the tile at position (x,y) is WALL, change it to FLOOR.
     */
    public static void toggle(TETile[][] world, int x, int y) {
        if (world[x][y].equals(Tileset.FLOOR)) {
            world[x][y] = Tileset.WALL;
        } else if (world[x][y].equals(Tileset.WALL)) {
            world[x][y] = Tileset.FLOOR;
        }
    }

    public static void main(String[] args) {
        // Initialize the tile rendering engine with a window of size WIDTH x HEIGHT.
        // This WIDTH and HEIGHT is very small!
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // Fill in the tile grid with FLOOR tiles.
        // If you don't fill in the grid, you'll get a NullPointerException!
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.FLOOR;
            }
        }

        char c; // Variable for saving the most recent character typed by the user.

        // This outer infinite-loop allows the game to continue indefinitely, until the user quits.
        while (true) {

            // hasNextKeyTyped checks if the user has typed a key that we haven't processed.
            // This loop runs until all unprocessed keys are processed.
            // If there are no unprocessed keys, we go back to the outer infinite loop to wait for the next key.
            while (StdDraw.hasNextKeyTyped()) {

                // nextKeyTyped returns the next key to process.
                // Always check hasNextKeyTyped before calling nextKeyTyped.
                // If you call nextKeyTyped and there's no key to process, code will crash!
                c = StdDraw.nextKeyTyped();

                c = Character.toLowerCase(c);

                // Switch statements can be useful to replace long if-else statements!
                switch (c) {
                    case '1':
                        toggle(world, 0, 0);
                        break;
                    case '2':
                        toggle(world, 1, 0);
                        break;
                    case '3':
                        toggle(world, 2, 0);
                        break;
                    case '4':
                        toggle(world, 3, 0);
                        break;
                    case '5':
                        toggle(world, 4, 0);
                        break;
                    case 'q':
                        System.exit(0); // Closes the game window and quits the game.
                        break;
                    default:
                        break;
                }

            }

            // Draws the world to the screen.
            // This is in the while(true) loop, because we want to frequently re-render the world.
            ter.renderFrame(world);
        }
    }
}