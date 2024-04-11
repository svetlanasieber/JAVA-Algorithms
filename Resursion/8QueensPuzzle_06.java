import java.util.HashSet;
import java.util.Set;

public class p06_8QueensPuzzle {
    private static final int BOARD_SIDE = 8;

    private static final Boolean[][] board = new Boolean[BOARD_SIDE][BOARD_SIDE];

    private static final Set<Integer> attackedRows = new HashSet<>();
    private static final Set<Integer> attackedCols = new HashSet<>();
    private static final Set<Integer> attackedLeftDiagonal = new HashSet<>();
    private static final Set<Integer> attackedRightDiagonal = new HashSet<>();


    public static void main(String[] args) {
        placeQueen(0);
    }

    private static void placeQueen(int row) {
        if (row == BOARD_SIDE) {
            printSolution();
        } else {
            for (int col = 0; col < BOARD_SIDE; col++) {
                if (!canPlaceQueen(row, col)) {
                    continue;
                }
                mark(row, col);
                placeQueen(row + 1);
                clearMark(row, col);
            }
        }
    }

    private static void clearMark(int row, int col) {
        board[row][col] = false;
        attackedRows.remove(row);
        attackedCols.remove(col);
        attackedLeftDiagonal.remove(row + col);
        attackedRightDiagonal.remove(row - col);
    }

    private static void mark(int row, int col) {
        board[row][col] = true;
        attackedRows.add(row);
        attackedCols.add(col);
        attackedLeftDiagonal.add(row + col);
        attackedRightDiagonal.add(row - col);
    }

    private static boolean canPlaceQueen(int row, int col) {
        return !attackedRows.contains(row)
                && !attackedCols.contains(col)
                && !attackedLeftDiagonal.contains(row + col)
                && !attackedRightDiagonal.contains(row - col);
    }

    private static void printSolution() {
        for (int row = 0; row < BOARD_SIDE; row++) {
            for (int col = 0; col < BOARD_SIDE; col++) {
                System.out.print((board[row][col] != null && board[row][col]) ? "* " : "- ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
