/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpecializedCastle {
    static class Position {
        int row, col;

        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static final int[] ROW_MOVES = {-1, 1}; // Can move up or down
    private static final int[] COL_MOVES = {1, -1}; // Can move right or left

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter board size: ");
        int boardSize = scanner.nextInt();

        char[][] board = new char[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = '.';
            }
        }

        System.out.print("Enter number of soldiers: ");
        int numSoldiers = scanner.nextInt();

        System.out.println("Enter soldiers' positions (row and col): ");
        for (int i = 0; i < numSoldiers; i++) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            board[row][col] = 'S';
        }

        System.out.println("Enter castle starting position (row and col): ");
        int startRow = scanner.nextInt();
        int startCol = scanner.nextInt();
        Position start = new Position(startRow, startCol);
        board[start.row][start.col] = 'C';

        List<List<Position>> allPaths = new ArrayList<>();
        findAllPaths(board, start, start, new ArrayList<>(), allPaths);

        // Print all paths
        for (List<Position> path : allPaths) {
            for (Position pos : path) {
                System.out.print("(" + pos.row + ", " + pos.col + ") ");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void findAllPaths(char[][] board, Position current, Position start, List<Position> path, List<List<Position>> allPaths) {
        // Add current position to the path
        path.add(new Position(current.row, current.col));

        // If we've returned to the start position and the path length is greater than 1, record the path
        if (current.row == start.row && current.col == start.col && path.size() > 1) {
            allPaths.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            return;
        }

        // Try to move in all possible directions
        for (int i = 0; i < 2; i++) {
            int newRow = current.row + ROW_MOVES[i];
            int newCol = current.col;

            // Check if the new position is within bounds and either empty or contains a soldier
            if (isValid(board, newRow, newCol)) {
                // Temporarily mark the board
                char temp = board[newRow][newCol];
                board[newRow][newCol] = '.';

                // Move to the left after killing a soldier
                if (temp == 'S') {
                    if (isValid(board, newRow, newCol - 1)) {
                        board[newRow][newCol - 1] = '.';
                        findAllPaths(board, new Position(newRow, newCol - 1), start, path, allPaths);
                        board[newRow][newCol - 1] = 'S';
                    }
                } else {
                    findAllPaths(board, new Position(newRow, newCol), start, path, allPaths);
                }

                // Unmark the board
                board[newRow][newCol] = temp;
            }
        }

        // Remove current position from the path before backtracking
        path.remove(path.size() - 1);
    }

    private static boolean isValid(char[][] board, int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }
}

