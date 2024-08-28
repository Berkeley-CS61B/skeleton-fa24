package game2048logic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static com.google.common.truth.Truth.assertWithMessage;

/** Tests the tiltColumn() method of Model.
 *
 *
 * @author Erik Kizior
 */
@Timeout(value = 60, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
public class TestTask7 {

    /** No merging required for this test. */
    @Test
    @Tag("task7")
    @DisplayName("No merge")
    public void testNoMergeColumn() {
        int[][] board = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {4, 0, 0, 0},
                {2, 0, 0, 0}
        };
        Model before = new Model(board, 0);
        before.tiltColumn(0);

        int[][] result = {
                {4, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        Model after = new Model(result, 0);
        assertWithMessage("Boards should match:").that(before.toString()).isEqualTo(after.toString());
    }

    /** One merge required. Not dependent on score being implemented.
     *  Expects only one column to be processed. */
    @Test
    @Tag("task7")
    @DisplayName("Merge, no score")
    public void testMergingColumn() {
        int[][] board = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 4, 0, 0},
                {4, 4, 4, 4}
        };
        Model before = new Model(board, 0);
        before.tiltColumn(1);

        int[][] result = {
                {0, 8, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {4, 0, 4, 4}
        };

        Model after = new Model(result, before.score());
        assertWithMessage("Boards should match:").that(before.toString()).isEqualTo(after.toString());
    }

    /** Same tiles separated. Not dependent on score being implemented.
     *  Expects only one column to be processed.*/
    @Test
    @Tag("task7")
    @DisplayName("Merge, no score")
    public void testNoMergeThroughTilesTilt() {
        int[][] board = {
                {4, 0, 0, 4},
                {0, 0, 0, 0},
                {8, 0, 0, 8},
                {4, 0, 0, 4}
        };
        Model before = new Model(board, 0);
        before.tiltColumn(3);

        int[][] result = {
                {4, 0, 0, 4},
                {0, 0, 0, 8},
                {8, 0, 0, 4},
                {4, 0, 0, 0}
        };

        Model after = new Model(result, before.score());
        assertWithMessage("Boards should match:").that(before.toString()).isEqualTo(after.toString());
    }

    /** Checks that a column tilt won't merge merged tiles. */
    @Test
    @Tag("task7")
    @DisplayName("no merge with merged")
    public void testNoMergeWithMergedCol() {
        int[][] board = new int[][]{
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 4, 0},
                {0, 0, 0, 0},
        };

        Model before = new Model(board, 0);
        before.tiltColumn(2);


        int[][] result = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };

        Model after = new Model(result, before.score());
        assertWithMessage("Boards should match:").that(before.toString()).isEqualTo(after.toString());
    }
}
