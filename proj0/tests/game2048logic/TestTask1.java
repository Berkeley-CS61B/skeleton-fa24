package game2048logic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static com.google.common.truth.Truth.assertWithMessage;


/** Tests the emptySpaceExists() method of Model.
 *
 * @author Omar Khan, Erik Kizior
 */
@Timeout(value = 60, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
public class TestTask1 {

    /** Note that this isn't a possible board state. */
    @Test
    @Tag("task1")
    @DisplayName("Fully empty board")
    public void testCompletelyEmpty() {
        int[][] rawVals = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        Model m = new Model(rawVals, 0);
        assertWithMessage("Board is full of empty space\n" + m.getBoard()).that(m.emptySpaceExists()).isTrue();
    }

    /** Tests a board that is completely full except for the top row. */
    @Test
    @Tag("task1")
    @DisplayName("Empty top row")
    public void testEmptyTopRow() {
        int[][] rawVals = new int[][] {
                {0, 0, 0, 0},
                {2, 4, 2, 4},
                {4, 2, 4, 2},
                {2, 4, 2, 4},
        };
        Model m = new Model(rawVals, 0);
        assertWithMessage("Top row is empty\n" + m.getBoard()).that(m.emptySpaceExists()).isTrue();

    }

    /** Tests a board that is completely full except for the bottom row. */
    @Test
    @Tag("task1")
    @DisplayName("Empty bottom row")
    public void testEmptyBottomRow() {
        int[][] rawVals = new int[][] {
                {2, 4, 2, 4},
                {4, 2, 4, 2},
                {2, 4, 2, 4},
                {0, 0, 0, 0},
        };
        Model m = new Model(rawVals, 0);
        assertWithMessage("Bottom row is empty\n" + m.getBoard()).that(m.emptySpaceExists()).isTrue();

    }


    /** Tests a board that is completely full except for the left column. */
    @Test
    @Tag("task1")
    @DisplayName("Empty left column")
    public void testEmptyLeftCol() {
        int[][] rawVals = new int[][] {
                {0, 4, 2, 4},
                {0, 2, 4, 2},
                {0, 4, 2, 4},
                {0, 2, 4, 2},
        };
        Model m = new Model(rawVals, 0);
        assertWithMessage("Left col is empty\n" + m.getBoard()).that(m.emptySpaceExists()).isTrue();
    }

    /** Tests a board that is completely full except for the right column. */
    @Test
    @Tag("task1")
    @DisplayName("Empty right column")
    public void testEmptyRightCol() {
        int[][] rawVals = new int[][] {
                {2, 4, 2, 0},
                {4, 2, 4, 0},
                {2, 4, 2, 0},
                {4, 2, 4, 0},
        };
        Model m = new Model(rawVals, 0);
        assertWithMessage("Right col is empty\n" + m.getBoard()).that(m.emptySpaceExists()).isTrue();
    }

    /** Tests a completely full board except one piece. */
    @Test
    @Tag("task1")
    @DisplayName("One empty space")
    public void testAlmostFullBoard() {
        int[][] rawVals = new int[][] {
                {2, 4, 2, 4},
                {4, 2, 4, 2},
                {2, 0, 2, 4},
                {4, 2, 4, 2},
        };
        Model m = new Model(rawVals, 0);
        assertWithMessage("Board is not full\n" + m.getBoard()).that(m.emptySpaceExists()).isTrue();
    }

    /** Tests a completely full board.
     * The game isn't over since you can merge, but the emptySpaceExists method
     * should only look for empty space (and not adjacent values).
     */
    @Test
    @Tag("task1")
    @DisplayName("Full board with valid merge")
    public void testFullBoard() {
        int[][] rawVals = new int[][] {
                {2, 2, 2, 2},
                {2, 2, 2, 2},
                {2, 2, 2, 2},
                {2, 2, 2, 2},
        };
        Model m = new Model(rawVals, 0);
        assertWithMessage("Board is full\n" + m.getBoard()).that(m.emptySpaceExists()).isFalse();
    }

    /** Tests a completely full board. */
    @Test
    @Tag("task1")
    @DisplayName("Full board")
    public void testFullBoardNoMerge() {
        int[][] rawVals = new int[][] {
                {2, 4, 2, 4},
                {4, 2, 4, 2},
                {2, 4, 2, 4},
                {4, 2, 4, 2},
        };
        Model m = new Model(rawVals, 0);
        assertWithMessage("Board is full\n" + m.getBoard()).that(m.emptySpaceExists()).isFalse();
    }
}