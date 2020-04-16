// By - Tianyi Liu
#include <cstdio>
#include <cstring>
#include <cmath>

bool graph[3005][3005];
int n;

int check();

int main()
{
    //freopen("in.txt", "r", stdin);
    int cas;
    scanf("%d", &cas);
    for (int t = 0 ; t < cas ; t++)
    {
        memset(graph, 0, sizeof(graph));
        scanf("%d", &n);
        for (int i = 1 ; i <= n ; i++)
            for (int j = i + 1 ; j <= n ; j++)
                    scanf("%d", &graph[i][j]);
        int flag = check();
        if (flag)
            puts("Great Team!");
        else
            puts("Bad Team!");
    }
    return 0;
}

int check()
{
    for (int i = 1 ; i <= n - 2 ; i++)
    {
        for (int j = i + 1 ; j <= n - 1 ; j++)
        {
            for (int k = j + i ; k <= n ; k++)
            {
                int flag = graph[i][j] + graph[i][k] + graph[j][k];
                if (flag == 3 || !flag)
                    return 0;
            }
        }
    }
    return 1;
}