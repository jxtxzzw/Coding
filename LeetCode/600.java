int MAX = 30;
public int findIntegers(int n) {
    int[] fib = new int[MAX];
    fib[0] = 1;
    fib[1] = 2;
    for (int i = 2; i < fib.length; i++) {
        fib[i] = fib[i - 1] + fib[i - 2];
    }
    int ans = 0;
    for (int i = MAX; i >= 0; i--) {
        boolean thisOne = (n & (1 << i)) != 0;
        boolean prevOne = (n & (i << (i + 1))) != 0;
        if (thisOne && prevOne) {
            ans = ans + fib[i] - 1;
        } else if (thisOne) {
            ans = ans + fib[i];
        }
    }
    return ans + 1;
}
