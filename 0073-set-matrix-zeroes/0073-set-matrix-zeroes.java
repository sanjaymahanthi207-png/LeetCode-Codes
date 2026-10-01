class Solution {
    public void setZeroes(int[][] matrix) {
        boolean zeroinFirstCol = false;
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Step 1: Mark zeroes in the first row and column
        for (int row = 0; row < rows; row++) {
            if (matrix[row][0] == 0) {
                zeroinFirstCol = true;
            }
            for (int col = 1; col < cols; col++) {
                if (matrix[row][col] == 0) {
                    matrix[row][0] = 0;
                    matrix[0][col] = 0;
                }
            }
        }

        // Step 2: Iterate backwards to update cells based on markers
        for (int row = rows - 1; row >= 0; row--) {
            for (int col = cols - 1; col >= 1; col--) {
                if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0;
                }
            }
            if (zeroinFirstCol) {
                matrix[row][0] = 0;
            }
        }
    }
}