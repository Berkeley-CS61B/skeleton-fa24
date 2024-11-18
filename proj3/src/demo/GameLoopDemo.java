package demo;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

public class GameLoopDemo {
    private static final int WIDTH = 5;
    private static final int HEIGHT = 1;

    /* If the tile at position (x,y) is FLOOR, change it to WALL.
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
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.FLOOR;
            }
        }

        char c;
        while (true) {
            while (StdDraw.hasNextKeyTyped()) {
                c = StdDraw.nextKeyTyped();

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
                        System.exit(0);
                        break;
                    case 'Q':
                        System.exit(0);
                        break;
                    default:
                        break;
                }

            }

            // draws the world to the screen
            ter.renderFrame(world);
        }
    }
}