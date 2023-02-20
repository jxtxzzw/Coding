
import java.util.Scanner;

/**
 * 我们可以很容易的统计俩张卡片是否在同一个队列中，用并查集就可以了。
 * 关键是怎么计算，在一个队列中的俩个卡片之间卡片数目，只要维护一下每个卡片到队列头的卡片数目就好了。
 * 在计算同一队列中的俩个卡片之间卡片数目，只要把俩个卡片到队列头的卡片数目做差就可以了。
 */

public class Main {

    static final int MAX = 30007;
    static int[] fa = new int[MAX];
    static int[] dist = new int[MAX];
    static int[] size = new int[MAX];

    static int find(int x) {
        // x == fa[x] ? x : (fa[x] = find(fa[x]));
        if (x != fa[x]) {
            // 除了递归更新 fa，还需要递归更新 dist
            int tmpF = fa[x]; // 但是需要先留下此刻的 fa[x]
            fa[x] = find(fa[x]);
            dist[x] += dist[tmpF]; // 要把递归更新过的 dist 一层层带下来，用旧的 fa[x] 即 tmpF
        }
        return fa[x];
    }

    static void union(int i, int j) {
        int fi = find(i);
        int fj = find(j);
        assert (fi != fj); // 输入保证第 i 号卡片与第 j 号卡片不在同一列
        fa[fi] = fj; // i 放到 j 的后面，所以更新 i 的 fa
        assert (dist[fi] == 0);
        dist[fi] += size[fj]; // 加上现有的 j 的长度
        size[fj] += size[fi]; // 更新 j 的长度
    }

    public static void main(String[] args) {
        for (int i = 0; i < MAX; i++) {
            fa[i] = i;
            dist[i] = 0;
            size[i] = 1;
        }
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            String action;
            int i, j;
            action = in.next();
            i = in.nextInt();
            j = in.nextInt();
            if (action.equals("M")) {
                union(i, j);
            } else {
                assert(action.equals("C"));
                int fi = find(i);
                int fj = find(j);
                if (fi == fj) {
                    System.out.println(Math.abs(dist[i] - dist[j]) - 1); // 如果在同一列中，那么它们之间一共有多少张卡片
                } else {
                    System.out.println(-1); // 如果第 i 号卡片与第 j 号卡片当前不在同一个队列种中，则输出 −1
                }
            }
        }
    }
}
