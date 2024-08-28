package game2048logic;

import game2048rendering.Side;
import game2048rendering.Tile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static com.google.common.truth.Truth.assertWithMessage;
import static game2048logic.TestUtils.checkTilt;;

/** Integration Tests for all methods. Broken into three sections:
 *  Tilt Tests, Multiple Move Tests, and NxN Tests.
 *
 * @author Samuel Berkun, Ergun Acikoz, Erik Kizior
 */
@Timeout(value = 60, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
public class TestIntegration {

    /*
     * ******************
     * *  TESTING TILT  *
     * ******************
     * <p>
     * The following tests determine the correctness of your `tilt`
     * method.
     */

    /** Checks that the right two pieces merge when 3 adjacent pieces have same value. */
    @Test
    @Tag("integration")
    @DisplayName("3 tile merge")
    public void testTripleMerge1() {
        int[][] before = new int[][]{
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {4, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.NORTH);
    }

    /** Checks that the right two pieces merge when 3 adjacent pieces have same value. */
    @Test
    @Tag("integration")
    @DisplayName("3 tile merge")
    public void testTripleMerge2() {
        int[][] before = new int[][]{
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0},
                {4, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.SOUTH);
    }

    /** Checks two adjacent merges work. */
    @Test
    @Tag("integration")
    @DisplayName("adjacent merge")
    public void testQuadrupleMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 2},
                {0, 0, 0, 2},
                {0, 0, 0, 2},
                {0, 0, 0, 2},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 4},
                {0, 0, 0, 4},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 8), Side.NORTH);
    }

    /** Checks that a tile only merges once per tilt. */
    @Test
    @Tag("integration")
    @DisplayName("One merge per North tilt")
    public void testSingleMergeUp() {
        int[][] before = new int[][]{
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {4, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {4, 0, 0, 0},
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.NORTH);
    }

    /** Checks that a tile only merges once per tilt. */
    @Test
    @Tag("integration")
    @DisplayName("One merge per South tilt")
    public void testSingleMergeSouth() {
        int[][] before = new int[][]{
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0},
                {2, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {4, 0, 0, 0},
                {4, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.SOUTH);
    }

    /** Checks that a tile only merges once per tilt. */
    @Test
    @Tag("integration")
    @DisplayName("One merge per East tilt")
    public void testSingleMergeEast() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {4, 0, 2, 2},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 4, 4},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.EAST);
    }

    /** Checks that a tile only merges once per tilt. */
    @Test
    @Tag("integration")
    @DisplayName("One merge per West tilt")
    public void testSingleMergeWest() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 2, 0, 4},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {4, 4, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.WEST);
    }


    /** Merge adjacent tiles up. */
    @Test
    @Tag("integration")
    @DisplayName("Up tilt with merge")
    public void testUpAdjacentMerge() {
        int[][] before = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.NORTH);
    }

    /** Merge non-adjacent tiles up. */
    @Test
    @Tag("integration")
    @DisplayName("Up tilt with gap and merge")
    public void testUpNonAdjacentMerge() {
        int[][] before = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.NORTH);
    }

    /** Move and merge adjacent tiles up. */
    @Test
    @Tag("integration")
    @DisplayName("Up tilt with gaps and merge")
    public void testUpAdjacentMergeMove() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.NORTH);
    }

    /** Merge adjacent tiles right. */
    @Test
    @Tag("integration")
    @DisplayName("Adjacent right merge")
    public void testRightAdjacentMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 2},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 4},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.EAST);
    }

    /** Merge non-adjacent tiles right. */
    @Test
    @Tag("integration")
    @DisplayName("Right merge with gap")
    public void testRightNonAdjacentMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 2},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 4},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.EAST);
    }

    /** Move and merge adjacent tiles right. */
    @Test
    @Tag("integration")
    @DisplayName("Adjacent merge with gaps")
    public void testRightAdjacentMergeMove() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 2, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 4},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.EAST);
    }

    /** Move and merge non-adjacent tiles right. */
    @Test
    @Tag("integration")
    @DisplayName("Right merge with gaps")
    public void testRightNonAdjacentMergeMove() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 2, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 4},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.EAST);
    }

    /** Don't merge non-adjacent divided tiles right. */
    @Test
    @Tag("integration")
    @DisplayName("Right no merge with gaps")
    public void testRightNonAdjacentNoMerge() {
        int[][] before = new int[][]{
                {2, 4, 0, 2},
                {2, 0, 4, 2},
                {2, 4, 2, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 2, 4, 2},
                {0, 2, 4, 2},
                {0, 2, 4, 2},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.EAST);
    }

    /** Merge adjacent tiles down. */
    @Test
    @Tag("integration")
    @DisplayName("Adjacent down merge")
    public void testDownAdjacentMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 2, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 4, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.SOUTH);
    }

    /** Merge non-adjacent tiles down. */
    @Test
    @Tag("integration")
    @DisplayName("Down merge")
    public void testDownNonAdjacentMerge() {
        int[][] before = new int[][]{
                {0, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 4, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.SOUTH);
    }

    /** Move and merge adjacent tiles down. */
    @Test
    @Tag("integration")
    @DisplayName("Adjacent down move and merge")
    public void testDownAdjacentMergeMove() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 4, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.SOUTH);
    }

    /** Move and merge non-adjacent tiles down. */
    @Test
    @Tag("integration")
    @DisplayName("Down move and merge")
    public void testDownNonAdjacentMergeMove() {
        int[][] before = new int[][]{
                {0, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 4, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.SOUTH);
    }

    /** Don't merge non-adjacent divided tiles right. */
    @Test
    @Tag("integration")
    @DisplayName("Down no merge with gaps")
    public void testDownNonAdjacentNoMerge() {
        int[][] before = new int[][]{
                {2, 2, 2, 0},
                {4, 0, 4, 0},
                {2, 4, 0, 0},
                {0, 2, 2, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {2, 2, 2, 0},
                {4, 4, 4, 0},
                {2, 2, 2, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.SOUTH);
    }

    /** Merge adjacent tiles left. */
    @Test
    @Tag("integration")
    @DisplayName("Left adjacent merge")
    public void testLeftAdjacentMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {2, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.WEST);
    }

    /** Merge non-adjacent tiles left. */
    @Test
    @Tag("integration")
    @DisplayName("Left merge")
    public void testLeftNonAdjacentMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {2, 0, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.WEST);
    }

    /** Move and merge adjacent tiles left. */
    @Test
    @Tag("integration")
    @DisplayName("Adjacent merge and move")
    public void testLeftAdjacentMergeMove() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 2, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.WEST);
    }

    /** Move and merge non-adjacent tiles left. */
    @Test
    @Tag("integration")
    @DisplayName("Merge and move with gaps")
    public void testLeftNonAdjacentMergeMove() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 2, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.WEST);
    }


    /*
     * *************************
     * *  MULTIPLE MOVE TESTS  *
     * *************************
     * <p>
     * The following tests will call the `tilt` method multiple times and check
     * the correctness of the board after each move. You shouldn't expect these
     * tests to pass until all of the above tests pass.
     */



    /** Will test multiple moves on the Model. */
    @Test
    @Tag("integration")
    @DisplayName("Multiple moves")
    public void testMultipleMoves1() {
        Model model = new Model(new int[][]{
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 2}
        }, 0);

        checkTilt(model, new Model(new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 2}
        }, 0), Side.EAST);

        model.addTile(Tile.create(2, 3, 1));
        checkTilt(model, new Model(new int[][]{
                {0, 0, 0, 4},
                {0, 0, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        }, 4), Side.NORTH);

        model.addTile(Tile.create(2, 0, 1));
        checkTilt(model, new Model(new int[][]{
                {0, 0, 0, 4},
                {0, 0, 0, 2},
                {0, 0, 0, 2},
                {0, 0, 0, 0}
        }, 4), Side.EAST);

        model.addTile(Tile.create(4, 2, 0));
        checkTilt(model, new Model(new int[][]{
                {0, 0, 4, 4},
                {0, 0, 0, 4},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        }, 8), Side.NORTH);

        model.addTile(Tile.create(4, 0, 3));
        checkTilt(model, new Model(new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {4, 0, 4, 8}
        }, 16), Side.SOUTH);
    }

    /** Will test multiple moves on the Model that end the game. */
    @Test
    @Tag("integration")
    @DisplayName("Multiple moves and end behavior")
    public void testMultipleMoves2() {
        Model model = new Model(new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 256, 256, 0},
                {1024, 0, 0, 512}
        }, 0);

        checkTilt(model, new Model(new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 512},
                {0, 0, 1024, 512}
        }, 512), Side.EAST);

        model.addTile(Tile.create(2, 0, 0));
        checkTilt(model, new Model(new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 1024, 1024}
        }, 1536), Side.SOUTH);

        model.addTile(Tile.create(2, 0, 1));
        checkTilt(model, new Model(new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0},
                {2, 2048, 0, 0}
        }, 3584), Side.WEST);
        assertWithMessage("Game is over. Tile with 2048 is on board:"
                + model).that(model.gameOver()).isTrue();
    }

    /** Will test multiple moves on the Model. */
    @Test
    @Tag("integration")
    @DisplayName("Multiple Moves 2")
    public void testMultipleMoves3() {
        Model model = new Model(new int[][]{
                {0, 2, 2, 0},
                {4, 0, 4, 0},
                {4, 0, 8, 0},
                {8, 0, 0, 0}
        }, 10);

        checkTilt(model, new Model(new int[][]{
                {0, 0, 0, 4},
                {0, 0, 0, 8},
                {0, 0, 4, 8},
                {0, 0, 0, 8}
        }, 22), Side.EAST);

        model.addTile(Tile.create(2, 1, 2));
        checkTilt(model, new Model(new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 4},
                {0, 0, 0, 8},
                {0, 2, 4, 16}
        }, 38), Side.SOUTH);

        model.addTile(Tile.create(2, 1, 1));
        checkTilt(model, new Model(new int[][]{
                {0, 4, 4, 4},
                {0, 0, 0, 8},
                {0, 0, 0, 16},
                {0, 0, 0, 0}
        }, 42), Side.NORTH);

        model.addTile(Tile.create(4, 0, 0));
        checkTilt(model, new Model(new int[][]{
                {4, 4, 4, 4},
                {0, 0, 0, 8},
                {0, 0, 0, 16},
                {0, 0, 0, 0}
        }, 42), Side.NORTH);

        model.addTile(Tile.create(2, 3, 0));
        checkTilt(model, new Model(new int[][]{
                {0, 0, 8, 8},
                {0, 0, 0, 8},
                {0, 0, 0, 16},
                {0, 0, 0, 2}
        }, 58), Side.EAST);
    }

    @Test
    @Tag("integration")
    @DisplayName("Consecutive merges onto same tile")
    public void testMergesOneTile() {
        Model model = new Model(new int[][]{
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 0, 0}
        }, 0);

        checkTilt(model, new Model(new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 2},
                {0, 0, 0, 0}
        }, 0), Side.EAST);

        model.addTile(Tile.create(4, 3, 0));

        checkTilt(model, new Model(new int[][]{
                {0, 0, 0, 4},
                {0, 0, 0, 4},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        }, 4), Side.NORTH);

        checkTilt(model, new Model(new int[][]{
                {0, 0, 0, 8},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        }, 12), Side.NORTH);
    }

    /*
     * *************************
     * *****  N X N TESTS  *****
     * *************************
     * <p>
     * The following tests will call the `tilt` method on boards of different sizes.
     */

    /** Tilting an empty 1 by 1 */
    @Test
    @Tag("integration")
    @DisplayName("The ants go marching")
    public void testOne() {
        int[][] before = new int[][] {
                {0},
        };
        int[][] after = new int[][] {
                {0},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.NORTH);
    }

    @Test
    @Tag("integration")
    @DisplayName("Non-tilt methods")
    public void testNonTiltMethods() {

        int[][] rawVals = new int[][] {
                {32, 4, 2},
                {64, 2, 4},
                {0, 4, 2},
        };
        Model m = new Model(rawVals, 0);
        assertWithMessage("N = 3, TestEmptySpace - (0,0) is empty\n" + m.getBoard()).that(m.emptySpaceExists()).isTrue();

        rawVals = new int[][] {
                {0, 0},
                {0, 2048},
        };
        m = new Model(rawVals, 0);
        assertWithMessage("N = 2, TestTask2 - One 2048 tile on board\n" + m.getBoard()).that(m.maxTileExists()).isTrue();

        rawVals = new int[][] {
                {32, 4, 2, 4, 8},
                {64, 2, 4, 2, 16},
                {32, 4, 2, 4, 8},
                {64, 2, 4, 2, 16},
                {32, 4, 2, 4, 8},
        };
        m = new Model(rawVals, 0);
        assertWithMessage("N = 5, TestTask3 - No move exists\n" + m.getBoard()).that(m.atLeastOneMoveExists()).isFalse();

    }

    @Test
    @Tag("integration")
    @DisplayName("Non-merged tilts for N = 1, 2, 3")
    public void testSmallNonMergedTilts() {
        int[][] before;
        int[][] after;

        before = new int[][] {
                {0},
        };
        after = new int[][] {
                {0},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.NORTH);

        before = new int[][] {
                {0, 0},
                {2, 2},
        };
        after = new int[][] {
                {2, 2},
                {0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.NORTH);

        before = new int[][] {
                {0, 2},
                {2, 0},
        };
        after = new int[][] {
                {2, 0},
                {2, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.WEST);

        before = new int[][] {
                {4, 0, 4},
                {2, 16, 2},
                {0, 0, 8},
        };
        after = new int[][] {
                {0, 0, 4},
                {4, 0, 2},
                {2, 16, 8},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.SOUTH);
    }


    /** Tilts for N = 1, 2, 3 */
    @Test
    @Tag("integration")
    @DisplayName("Tilts for N = 1, 2, 3")
    public void testSmallTilts() {
        int[][] before;
        int[][] after;

        before = new int[][] {
                {4},
        };
        after = new int[][] {
                {4},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.NORTH);

        before = new int[][] {
                {2, 2},
                {0, 2},
        };
        after = new int[][] {
                {4, 0},
                {2, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.WEST);

        before = new int[][] {
                {8, 0, 2},
                {0, 0, 2},
                {0, 0, 2},
        };
        after = new int[][] {
                {0, 0, 0},
                {0, 0, 2},
                {8, 0, 4},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.SOUTH);
    }

    /** gameOver for N = 1, 2, 3 */
    @Test
    @Tag("integration")
    @DisplayName("Tilts for N = 1, 2, 3")
    public void testSmallGameOver() {
        Model model;

        model = new Model(new int[][]{
                {0}
        }, 0);
        assertWithMessage("Game is not over. Empty space exists:"
                + model).that(model.gameOver()).isFalse();


        model = new Model(new int[][]{
                {2}
        }, 0);
        assertWithMessage("Game is over. No tilt would result in a change:"
                + model).that(model.gameOver()).isTrue();


        model = new Model(new int[][]{
                {2, 2},
                {4, 8}
        }, 0);
        assertWithMessage("Game is not over. A tilt left or right would result in a merge:"
                + model).that(model.gameOver()).isFalse();

        model = new Model(new int[][]{
                {2, 4},
                {4, 2}
        }, 0);
        assertWithMessage("Game is over. No tilt would result in a change:"
                + model).that(model.gameOver()).isTrue();

        model = new Model(new int[][]{
                {2, 2, 2},
                {2, 2, 1024},
                {2, 2, 2}
        }, 0);
        assertWithMessage("Game is not over. A tilt in any direction would result in a merge:"
                + model).that(model.gameOver()).isFalse();

        model = new Model(new int[][]{
                {2, 2, 2},
                {2, 2, 2048},
                {2, 2, 2}
        }, 0);
        assertWithMessage("Game is over. Max tile exists:"
                + model).that(model.gameOver()).isTrue();
    }


    /** Tilt and gameOver for N = 20 */
    @Test
    @Tag("integration")
    public void testLarge() {
        int[][] before = new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 4, 4, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 4, 4, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0},
                {0, 0, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0},
                {0, 0, 0, 4, 4, 4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0},
                {0, 0, 0, 0, 0, 4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0},
                {0, 0, 4, 0, 0, 4, 0, 0, 0, 4, 4, 0, 0, 0, 0, 0, 4, 4, 0, 0},
                {0, 0, 4, 0, 0, 4, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0},
                {0, 0, 4, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 4, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 4, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 4, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 4, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 4, 4, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 0, 4, 4, 4, 0, 0, 0, 0, 4, 4, 4, 4, 0, 0, 4, 0, 0, 0},
                {0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 4, 4, 0, 0, 0, 4, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 4, 4, 0, 0, 0, 4, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 4, 0, 4, 4, 4, 4, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        int[][] after = new int[][] {
                {8, 8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 8, 8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        Model beforeModel = new Model(before, 0);
        assertWithMessage("Game is not over. Empty space exists:"
                + beforeModel).that(beforeModel.gameOver()).isFalse();
        checkTilt(beforeModel, new Model(after, 312), Side.WEST);
    }
}
