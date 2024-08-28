package game2048logic;
import game2048rendering.Side;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static com.google.common.truth.Truth.assertWithMessage;
import static game2048logic.TestUtils.checkTilt;

/** Tests the tilt() method in the up (Side.NORTH) direction only.
 *
 * @author Omar Khan, Erik Kizior
 */
@Timeout(value = 60, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
public class TestTask8 {

    /** Move tiles up (no merging). */
    @Test
    @Tag("task8")
    @DisplayName("Up Tilt")
    public void testUpNoMerge() {
        int[][] before = new int[][] {
                {0, 0, 4, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][] {
                {0, 0, 4, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.NORTH);
    }

    /** Move tiles up with a merge. Must merge in the proper order. Score does not matter. */
    @Test
    @Tag("task8")
    @DisplayName("Up Tilt")
    public void testUpMergeNoSkips() {
        int[][] board = new int[][] {
                {4, 4, 4, 4},
                {2, 2, 2, 2},
                {2, 2, 2, 2},
                {4, 4, 4, 4},
        };

        Model before = new Model(board, 0);
        before.tiltWrapper(Side.NORTH);

        int[][] result = new int[][] {
                {4, 4, 4, 4},
                {4, 4, 4, 4},
                {4, 4, 4, 4},
                {0, 0, 0, 0},
        };

        Model after = new Model(result, before.score());
        assertWithMessage("Boards should match:").that(before.toString()).isEqualTo(after.toString());
    }

    /** Move tiles up with trickier merges. Score does not matter. */
    @Test
    @Tag("task8")
    @DisplayName("Up Tilt")
    public void testUpComplicated() {
        int[][] board = new int[][] {
                {4, 4, 4, 0},
                {0, 4, 8, 2},
                {2, 4, 2, 2},
                {4, 4, 2, 0},
        };

        Model before = new Model(board, 0);
        before.tiltWrapper(Side.NORTH);

        int[][] result = new int[][] {
                {4, 8, 4, 4},
                {2, 8, 8, 0},
                {4, 0, 4, 0},
                {0, 0, 0, 0},
        };

        Model after = new Model(result, before.score());
        assertWithMessage("Boards should match:").that(before.toString()).isEqualTo(after.toString());
    }
}
