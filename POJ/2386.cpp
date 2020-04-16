#include <stdio.h>

#define MAX_N 107
#define MAX_M 107
int N, M;
char field[MAX_N][MAX_M + 1];

void dfs(int x, int y) {
    field[x][y] = '.';
    for (int dx = -1; dx <= 1; dx++)
        for (int dy = -1; dy <= 1; dy++) {
            int nx = x + dx, ny = y + dy;
            if (0 <= nx && nx < N && 0 <= ny && ny < M && field[nx][ny] == 'W')dfs(nx, ny);
        }
    return;

}

void solve() {
    int res = 0;
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            if (field[i][j] == 'W') {
                // 从有W的地方开始dfs
                dfs(i, j);
                res++;
            }
        }
    }
    printf("%d\n", res);
}

int main() {
    scanf("%d%d", &N, &M);
    for (int i = 0; i < N; ++i) {
        scanf("%s", field[i]);
    }
    solve();

}
