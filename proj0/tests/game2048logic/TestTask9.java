package game2048logic;

import game2048rendering.Side;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static game2048logic.TestUtils.checkTilt;

/** Tests the tilt() method in all directions.
 *
 * @author Omar Khan, Erik Kizior
 */
@Timeout(value = 60, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
public class TestTask9 {


    /*
     * ******************
     * *  TESTING TILT  *
     * ******************
     * <p>
     * The following tests determine the correctness of your `tilt`
     * method.
     */


    /** Checks that a tilt that causes no change returns false. */
    @Test
    @Tag("task9")
    @DisplayName("Test invalid tilt output")
    public void testNoMove() {
        int[][] before = new int[][]{
                {2, 0, 2, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {2, 0, 2, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.NORTH);
    }

    /** Move tiles up (no merging). */
    @Test
    @Tag("task9")
    @DisplayName("Test Up tilt")
    public void testUpNoMerge() {
        int[][] before = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 4, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.NORTH);
    }

    /** Move adjacent tiles up (no merging). */
    @Test
    @Tag("task9")
    @DisplayName("Adjacent Up tilt")
    public void testUpAdjacentNoMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 4, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.NORTH);
    }

    /** Move non-adjacent tiles up (no merging). */
    @Test
    @Tag("task9")
    @DisplayName("Up tilt with gap")
    public void testUpNonAdjacentNoMerge1() {
        int[][] before = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.NORTH);
    }

    /** Move non-adjacent tiles up (no merging); case 2: both tiles move. */
    @Test
    @Tag("task9")
    @DisplayName("Up tilt with gaps")
    public void testMoveUpNonAdjacentNoMerge2() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 4, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.NORTH);
    }
    /** Move tiles right (no merging). */
    @Test
    @Tag("task9")
    @DisplayName("Right tilt")
    public void testRightNoMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 4},
                {0, 0, 2, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 4},
                {0, 0, 0, 2},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.EAST);
    }

    /** Move adjacent tiles right (no merging). */
    @Test
    @Tag("task9")
    @DisplayName("Adjacent right tilt")
    public void testRightAdjacentNoMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 4, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 4},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.EAST);
    }

    /** Move adjacent tiles right (no merging). */
    @Test
    @Tag("task9")
    @DisplayName("Adjacent right tilt with gap")
    public void testRightNonAdjacentNoMerge1() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 0, 4},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 4},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.EAST);
    }

    /** Move adjacent tiles right (no merging); case 2: both tiles move. */
    @Test
    @Tag("task9")
    @DisplayName("Adjacent right tilt with gaps")
    public void testRightNonAdjacentNoMerge2() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 4, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 4},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.EAST);
    }

    /** Move tiles down (no merging). */
    @Test
    @Tag("task9")
    @DisplayName("Down tilt")
    public void testDownNoMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 4, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 4, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.SOUTH);
    }

    /** Move adjacent tiles down (no merging). */
    @Test
    @Tag("task9")
    @DisplayName("Adjacent down tilt")
    public void testDownAdjacentNoMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 4, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 4, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.SOUTH);
    }

    /** Move non-adjacent tiles down (no merging). */
    @Test
    @Tag("task9")
    @DisplayName("Down tilt with gaps")
    public void testDownNonAdjacentNoMerge1() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 4, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 4, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.SOUTH);
    }

    @Test
    @Tag("task9")
    @DisplayName("Left tilt")
    public void testLeftNoMerge() {
        int[][] before = new int[][]{
                {4, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {4, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.WEST);
    }

    /** Move adjacent tiles left (no merging). */
    @Test
    @Tag("task9")
    @DisplayName("Adjacent left tilt")
    public void testLeftAdjacentNoMerge() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {0, 4, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {4, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.WEST);
    }

    /** Move non-adjacent tiles left (no merging). */
    @Test
    @Tag("task9")
    @DisplayName("Left tilt with gaps")
    public void testLeftNonAdjacentNoMerge1() {
        int[][] before = new int[][]{
                {0, 0, 0, 0},
                {4, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][]{
                {0, 0, 0, 0},
                {4, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.WEST);
    }
}

