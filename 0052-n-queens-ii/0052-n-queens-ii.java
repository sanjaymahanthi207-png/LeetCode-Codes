import java.util.*;

class Solution {
    public int totalNQueens(int n) {
        char board[][] = new char[n][n];
        for (char i[] : board)
            Arrays.fill(i, '.');
        return dfs(0, board);
    }

    public int dfs(int col, char board[][]) {
        if (col == board.length) return 1;
        
        int count = 0;
        for (int row = 0; row < board.length; row++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q';
                count += dfs(col + 1, board);
                board[row][col] = '.';
            }
        }
        return count;
    }

    public boolean isSafe(char board[][], int row, int col) {
        int dupRow = row;
        int dupCol = col;
        
        // Upper-left diagonal
        while (row >= 0 && col >= 0) {
            if (board[row][col] == 'Q') return false;
            row--;
            col--;
        }
        
        // Left column
        row = dupRow;
        col = dupCol;
        while (col >= 0) {
            if (board[row][col] == 'Q') return false;
            col--;
        }
        
        // Lower-left diagonal
        row = dupRow;
        col = dupCol;
        while (col >= 0 && row < board.length) {
            if (board[row][col] == 'Q') return false;
            col--;
            row++;
        }
        return true;
    }
}