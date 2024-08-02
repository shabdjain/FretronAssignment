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

      

    private static void findAllPaths(char[][] board, Position current, Position start, List<Position> path, List<List<Position>> allPaths) {
        // Add current position to the path
        path.add(new Position(current.row, current.col));
    
                // Temporarily mark the board
                char temp = board[newRow][newCol];
                board[newRow][newCol] = '.';

                

