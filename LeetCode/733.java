class Solution {

    private final int[][] DIRECT = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private int[][] image;
    private boolean[][] visited;

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        this.image = image;
        visited = new boolean[image.length][image[0].length];
        dfs(sr, sc, newColor);
        return this.image;
    }

    private void dfs(int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        image[sr][sc] = newColor;
        visited[sr][sc] = true;
        for (int[] d : DIRECT) {
            int x = sr + d[0];
            int y = sc + d[1];
            if (isValid(x, y, oldColor)) {
                dfs(x, y, newColor);
            }
        }
    }

    private boolean isValid(int x, int y, int oldColor) {
        return x >= 0 && y >= 0 && x < image.length && y < image[x].length && image[x][y] == oldColor && !visited[x][y];
    }
}
