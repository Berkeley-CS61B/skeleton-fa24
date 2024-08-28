package game2048logic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static com.google.common.truth.Truth.assertWithMessage;

/** Tests the moveTileUpAsFarAsPossible() method of Model with merges.
 *
 *
 * @author Erik Kizior
 */
@Timeout(value = 60, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
public class TestTask6 {

    /** Merging required. Tiles of same value in same column. Does not depend on the score. */
    @Test
    @Tag("task6")
    @DisplayName("two tiles merge no score")
    public void testTwoTilesMergeNoScore() {
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

        Model after = new Model(result, before.score());
        assertWithMessage("Boards should match:").that(before.toString()).isEqualTo(after.toString());
    }

    /** Checks that moving up will not attempt to merge tiles separated by another unequally-valued tile. */
    @Test
    @Tag("task6")
    @DisplayName("same tile separated, no merge")
    public void testNoMergeThroughTiles() {
        int[][] board = new int[][]{
                {8, 0, 0, 0},
                {4, 0, 0, 0},
                {8, 0, 0, 0},
                {0, 0, 0, 0},
        };

        Model before = new Model(board, 0);
        before.moveTileUpAsFarAsPossible(0, 1);

        int[][] result = new int[][]{
                {8, 0, 0, 0},
                {4, 0, 0, 0},
                {8, 0, 0, 0},
                {0, 0, 0, 0},
        };

        Model after = new Model(result, before.score());
        assertWithMessage("Boards should match:").that(before.toString()).isEqualTo(after.toString());
    }

    /** Checks that a tilt will merge with the first value above it. Note that this is
     *  action alone is not achievable in an actual game. */
    @Test
    @Tag("task6")
    @DisplayName("merge up, don't skip")
    public void testMergeNoSkip() {
        int[][] board = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
        };

        Model before = new Model(board, 0);
        before.moveTileUpAsFarAsPossible(2, 0);

        int[][] result = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 4, 0},
                {0, 0, 0, 0},
        };

        Model after = new Model(result, before.score());
        assertWithMessage("Boards should match:").that(before.toString()).isEqualTo(after.toString());
    }

    /** Checks that a tile won't merge with a merged tile. */
    @Test
    @Tag("task6")
    @DisplayName("no merge with merged")
    public void testNoMergeWithMerged() {
        int[][] board = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 4, 0},
        };

        Model testing = new Model(board, 0);
        testing.moveTileUpAsFarAsPossible(2, 1);

        int[][] intermediate = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 4, 0},
        };

        Model after1 = new Model(intermediate, testing.score());
        assertWithMessage("Boards should match:").that(testing.toString()).isEqualTo(after1.toString());

        testing.moveTileUpAsFarAsPossible(2, 0);

        int[][] result = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 4, 0},
                {0, 0, 4, 0},
                {0, 0, 0, 0},
        };

        Model after2 = new Model(result, after1.score());
        assertWithMessage("Boards should match:").that(testing.toString()).isEqualTo(after2.toString());
    }
}
