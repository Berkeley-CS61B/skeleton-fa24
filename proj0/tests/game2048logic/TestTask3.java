package game2048logic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static com.google.common.truth.Truth.assertWithMessage;

/** Tests the atLeastOneMoveExists() method of Model.
 *
 * You shouldn't expect to pass these tests until you're passing all the tests
 * in TestTask1.
 *
 * @author Omar Khan, Erik Kizior
 */
@Timeout(value = 60, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
public class TestTask3 {

    /** Tests a board with some empty space.
     *
     *  Note that this isn't a comprehensive test for empty space. For that,
     * see the TestTask1 class.
     */
    @Test
    @Tag("task3")
    @DisplayName("Empty Space Exists")
    public void testEmptySpace() {
        int[][] rawVals = new int[][] {
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 0, 0},
        };

        Model m = new Model(rawVals, 0);
        assertWithMessage("A tilt in any direction will change the board "
                        + "(there is empty space on the board)\n" + m.getBoard()).that(m.atLeastOneMoveExists()).isTrue();
    }

    /** Tests a board where a tilt in any direction would cause a change. */
    @Test
    @Tag("task3")
    @DisplayName("Valid Tilt Exists")
    public void testAnyDir() {
        int[][] rawVals = new int[][] {
                {2, 4, 2, 2},
                {4, 2, 4, 2},
                {2, 4, 2, 4},
                {4, 2, 4, 2},
        };

        Model m = new Model(rawVals, 0);
        assertWithMessage("A tilt in any direction will change the board\n"
                + m.getBoard()).that(m.atLeastOneMoveExists()).isTrue();
    }

    /** Tests a board where a tilt left or right would cause a change. */
    @Test
    @Tag("task3")
    @DisplayName("Valid Left/Right Tilt")
    public void testLeftOrRight() {
        int[][] rawVals = new int[][] {
                {2, 4, 2, 4},
                {4, 8, 4, 2},
                {2, 2, 2, 4},
                {4, 8, 4, 2},
        };

        Model m = new Model(rawVals, 0);
        assertWithMessage("A tilt left or right will change the board\n" + m.getBoard()).that(m.atLeastOneMoveExists()).isTrue();
    }

    /** Tests a board where a tilt up or down would cause a change. */
    @Test
    @Tag("task3")
    @DisplayName("Valid Up/Down Tilt")
    public void testUpOrDown() {
        int[][] rawVals = new int[][] {
                {2, 4, 2, 4},
                {4, 8, 4, 2},
                {2, 16, 4, 8},
                {4, 8, 4, 2},
        };

        Model m = new Model(rawVals, 0);
        assertWithMessage("A tilt up or down will change the board\n" + m.getBoard()).that(m.atLeastOneMoveExists()).isTrue();
    }

    /** Tests a board where some move exists (max tile is on the board).
     *
     * While having the max tile on the board does mean the game is over, it
     * should not be handled in this method.
     */
    @Test
    @Tag("task3")
    @DisplayName("Valid Tilt with Max Tile")
    public void testMoveExistsMaxPiece() {
        int[][] rawVals = new int[][] {
                {2, 4, 2, 4},
                {4, 2, 4, 2},
                {2, 2, 2, 4},
                {4, 2, 4, 2048},
        };

        Model m = new Model(rawVals, 0);
        assertWithMessage("A tilt in any direction will change the board\n"
                + m.getBoard()).that(m.atLeastOneMoveExists()).isTrue();
    }

    /** Tests a board where no move exists. */
    @Test
    @Tag("task3")
    @DisplayName("No Valid Move")
    public void testNoMoveExists1() {
        int[][] rawVals = new int[][] {
                {2, 4, 2, 4},
                {4, 2, 4, 2},
                {2, 4, 2, 4},
                {4, 2, 4, 2},
        };

        Model m = new Model(rawVals, 0);
        assertWithMessage("No move exists\n" + m.getBoard()).that(m.atLeastOneMoveExists()).isFalse();
    }

    /** Tests a board where no move exists. */
    @Test
    @Tag("task3")
    @DisplayName("No Valid Move")
    public void testNoMoveExists2() {
        int[][] rawVals = new int[][] {
                {2, 1024, 2, 4},
                {4, 2, 4, 2},
                {2, 8, 16, 4},
                {512, 2, 4, 2},
        };

        Model m = new Model(rawVals, 0);
        assertWithMessage("No move exists\n" + m.getBoard()).that(m.atLeastOneMoveExists()).isFalse();
    }

    /** Tests a board where no move exists. */
    @Test
    @Tag("task3")
    @DisplayName("No Valid Move")
    public void testNoMoveExists3() {
        int[][] rawVals = new int[][] {
                {8, 4, 2, 32},
                {32, 2, 4, 2},
                {2, 8, 2, 4},
                {4, 64, 4, 64},
        };

        Model m = new Model(rawVals, 0);
        assertWithMessage("No move exists\n" + m.getBoard()).that(m.atLeastOneMoveExists()).isFalse();
    }

    /** Tests a board where no move exists. */
    @Test
    @Tag("task3")
    @DisplayName("No Valid Move")
    public void testNoMoveExists4() {
        int[][] rawVals = new int[][] {
                {2, 4, 2, 32},
                {32, 2, 4, 2},
                {2, 128, 2, 4},
                {4, 2, 4, 2},
        };

        Model m = new Model(rawVals, 0);
        assertWithMessage("No move exists\n" + m.getBoard()).that(m.atLeastOneMoveExists()).isFalse();
    }

    /** Tests a board where no move exists. */
    @Test
    @Tag("task3")
    @DisplayName("No Valid Move")
    public void testNoMoveExists5() {
        int[][] rawVals = new int[][] {
                {8, 16, 2, 32},
                {32, 2, 64, 2},
                {2, 256, 128, 256},
                {1024, 8, 4, 2},
        };

        Model m = new Model(rawVals, 0);
        assertWithMessage("No move exists\n" + m.getBoard()).that(m.atLeastOneMoveExists()).isFalse();
    }

    /** Tests a board where one move exists in the top right corner. */
    @Test
    @Tag("task3")
    @DisplayName("Move exists in corner")
    public void testMoveExistsCorner1()  {
        int[][] rawVals = new int[][] {
                {8, 16, 4, 2},
                {32, 2, 64, 2},
                {2, 256, 128, 256},
                {1024, 8, 4, 2},
        };

        Model m = new Model(rawVals, 0);
        assertWithMessage("One move exists\n" + m.getBoard()).that(m.atLeastOneMoveExists()).isTrue();
    }

    /** Tests a board where one move exists in the top right corner. */
    @Test
    @Tag("task3")
    @DisplayName("Move exists in corner")
    public void testMoveExistsCorner2()  {
        int[][] rawVals = new int[][] {
                {8, 16, 2, 2},
                {32, 2, 64, 4},
                {2, 256, 128, 256},
                {1024, 8, 4, 2},
        };

        Model m = new Model(rawVals, 0);
        assertWithMessage("One move exists\n" + m.getBoard()).that(m.atLeastOneMoveExists()).isTrue();
    }

    /** Tests a board where one move exists in the top left corner. */
    @Test
    @Tag("task3")
    @DisplayName("Move exists in corner")
    public void testMoveExistsCorner3() {
        int[][] rawVals = new int[][] {
                {8, 8, 32, 2},
                {32, 2, 64, 4},
                {2, 256, 128, 256},
                {1024, 8, 4, 2},
        };

        Model m = new Model(rawVals, 0);
        assertWithMessage("One move exists\n" + m.getBoard()).that(m.atLeastOneMoveExists()).isTrue();
    }

    /** Tests a board where one move exists in the top left corner. */
    @Test
    @Tag("task3")
    @DisplayName("Move exists in corner")
    public void testMoveExistsCorner4()  {
        int[][] rawVals = new int[][] {
                {8, 16, 32, 2},
                {8, 2, 64, 4},
                {2, 256, 128, 256},
                {1024, 8, 4, 2},
        };

        Model m = new Model(rawVals, 0);
        assertWithMessage("One move exists\n" + m.getBoard()).that(m.atLeastOneMoveExists()).isTrue();
    }

    /** Tests a board where one move exists in the bottom left corner. */
    @Test
    @Tag("task3")
    @DisplayName("Move exists in corner")
    public void testMoveExistsCorner5()  {
        int[][] rawVals = new int[][] {
                {8, 16, 32, 2},
                {32, 2, 64, 4},
                {1024, 256, 128, 256},
                {1024, 8, 4, 2},
        };

        Model m = new Model(rawVals, 0);
        assertWithMessage("One move exists\n" + m.getBoard()).that(m.atLeastOneMoveExists()).isTrue();
    }

    /** Tests a board where one move exists in the bottom left corner. */
    @Test
    @Tag("task3")
    @DisplayName("Move exists in corner")
    public void testMoveExistsCorner6()  {
        int[][] rawVals = new int[][] {
                {8, 16, 32, 2},
                {8, 2, 64, 4},
                {2, 256, 128, 256},
                {1024, 1024, 4, 2},
        };

        Model m = new Model(rawVals, 0);
        assertWithMessage("One move exists\n" + m.getBoard()).that(m.atLeastOneMoveExists()).isTrue();
    }

    /** Tests a board where one move exists in the bottom right corner. */
    @Test
    @Tag("task3")
    @DisplayName("Move exists in corner")
    public void testMoveExistsCorner7()  {
        int[][] rawVals = new int[][] {
                {8, 16, 32, 2},
                {8, 2, 64, 4},
                {2, 256, 128, 2},
                {1024, 32, 4, 2},
        };

        Model m = new Model(rawVals, 0);
        assertWithMessage("One move exists\n" + m.getBoard()).that(m.atLeastOneMoveExists()).isTrue();
    }

    /** Tests a board where one move exists in the bottom right corner. */
    @Test
    @Tag("task3")
    @DisplayName("Move exists in corner")
    public void testMoveExistsCorner8()  {
        int[][] rawVals = new int[][] {
                {8, 16, 32, 2},
                {8, 2, 64, 4},
                {2, 256, 128, 256},
                {1024, 32, 2, 2},
        };

        Model m = new Model(rawVals, 0);
        assertWithMessage("One move exists\n" + m.getBoard()).that(m.atLeastOneMoveExists()).isTrue();
    }

    /** Tests a 2x2 board where one move exists. Remember that your board is not
     *  necessarily 4x4! */
    @Test
    @Tag("task3")
    @DisplayName("Move exists in corner")
    public void testMoveExistsSmall()  {
        int[][] rawVals = new int[][] {
                {2, 2},
                {0, 2},
        };

        Model m = new Model(rawVals, 0);
        assertWithMessage("One move exists\n" + m.getBoard()).that(m.atLeastOneMoveExists()).isTrue();
    }
}
