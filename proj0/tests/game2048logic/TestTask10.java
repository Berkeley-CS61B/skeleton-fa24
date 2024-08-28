package game2048logic;

import game2048rendering.Side;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertWithMessage;
import static game2048logic.TestUtils.checkTilt;

/** Tests score updates in all directions.
 *
 * @author Erik Kizior
 */
public class TestTask10 {

    /** Merging required. Tiles of same value in same column. Checks that score updates correctly. */
    @Test
    @Tag("task10")
    @DisplayName("two tiles merge with score update")
    public void testTwoTilesMergeScore() {
        int[][] board = {
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0}
        };
        Model before = new Model(board, 0);
        before.moveTileUpAsFarAsPossible(0, 0);

        int[][] result = {
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        Model after = new Model(result, 4);
        assertWithMessage("Boards should match:").that(before.toString()).isEqualTo(after.toString());
    }

    /** One merge required. Requires that score is implemented. */
    @Test
    @Tag("task10")
    @DisplayName("Merge and score")
    public void testMergingColumnWithScore() {
        int[][] board = {
                {0, 0, 0, 0},
                {2, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0}
        };
        Model before = new Model(board, 0);
        before.tiltColumn(0);

        int[][] result = {
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        Model after = new Model(result, 4);
        assertWithMessage("Boards should match:").that(before.toString()).isEqualTo(after.toString());
    }

    /** A basic merge. */
    @Test
    @Tag("task10")
    @DisplayName("Up merge")
    public void testUpBasicMerge() {
        int[][] before = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][] {
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.NORTH);
    }

    /** A triple merge. Only the leading 2 tiles should merge. */
    @Test
    @Tag("task10")
    @DisplayName("Triple merge")
    public void testUpTripleMerge() {
        int[][] before = new int[][] {
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
        };
        int[][] after = new int[][] {
                {0, 0, 4, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.NORTH);
    }

    /** A tricky merge.
     *
     * The tricky part here is that the 4 tile on the bottom row shouldn't
     * merge with the newly created 4 tile on the top row. If you're failing
     * this test, try seeing how you can ensure that the bottom 4 tile doesn't
     * merge with the newly created 4 tile on top.
     */
    @Test
    @Tag("task10")
    @DisplayName("Limit Merging")
    public void testUpTrickyMerge() {
        int[][] before = new int[][] {
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 4, 0},
        };
        int[][] after = new int[][] {
                {0, 0, 4, 0},
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.NORTH);
    }

    /** Assures that nothing happens if a tile shouldn't move. */
    @Test
    @Tag("task10")
    @DisplayName("no merging with self")
    public void testTileOnItselfNoScore() {
        int[][] board = {
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        int[][] result = {
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        checkTilt(new Model(board, 0), new Model(result, 0), Side.NORTH);
    }
}
