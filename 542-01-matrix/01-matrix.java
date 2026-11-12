class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int[] q = new int[n * m];
        int head = 0, tail = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    q[tail++] = i * m + j;
                } else {
                    mat[i][j] = -1; // mark unvisited
                }
            }
        }

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        while (head < tail) {
            int curr = q[head++];
            int r = curr / m, c = curr % m;
            int nextDist = mat[r][c] + 1;

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k], nc = c + dc[k];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && mat[nr][nc] == -1) {
                    mat[nr][nc] = nextDist;
                    q[tail++] = nr * m + nc;
                }
            }
        }

        return mat;
    }
}