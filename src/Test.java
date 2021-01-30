public class Test {
    public static void main(String[] args) {
//        int[] r = new Test().getLandLength(new int[]{0});

        int[][] grid = {{0, 1, 0, 0},
                {1, 1, 1, 1},
                {0, 1, 0, 0},
                {1, 1, 0, 0}};
        int r = new Test().islandPerimeter(grid);
        System.out.println("r=" + r);
    }

    public int islandPerimeter(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        int[] dx = new int[]{1, 0, -1, 0};
        int[] dy = new int[]{0, 1, 0, -1};
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int cnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int tx = i + dx[k];
                        int ty = j + dy[k];
                        if (tx < 0 || tx >= n || ty < 0 || ty >= m || grid[tx][ty] == 0) {
                            cnt++;
                        }
                    }
                    System.out.println("格子[" + (i + 1) + "," + (j + 1) + "]，有" + cnt + "个边作为周长。");
                    sum += cnt;
                }
            }
        }

        return sum;
    }
}
