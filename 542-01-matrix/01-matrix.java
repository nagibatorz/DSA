class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int maxDist = n + m; // safe upper bound without integer overflow

        // Pass 1: Top-Left to Bottom-Right
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] != 0) {
                    int top = (i > 0) ? mat[i - 1][j] : maxDist;
                    int left = (j > 0) ? mat[i][j - 1] : maxDist;
                    mat[i][j] = Math.min(top, left) + 1;
                }
            }
        }

        // Pass 2: Bottom-Right to Top-Left
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (mat[i][j] != 0) {
                    int bottom = (i < n - 1) ? mat[i + 1][j] : maxDist;
                    int right = (j < m - 1) ? mat[i][j + 1] : maxDist;
                    mat[i][j] = Math.min(mat[i][j], Math.min(bottom, right) + 1);
                }
            }
        }

        return mat;
    }
}