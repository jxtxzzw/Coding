public String pushDominoes(String dominoes) {
    int n = dominoes.length();
    int[] stateChangedTime = new int[n];
    char[] state = dominoes.toCharArray();
    for (int i = 1; i < n; i++) {
        if (state[i - 1] == 'R' && state[i] == '.') {
            state[i] = 'R';
            stateChangedTime[i] = stateChangedTime[i - 1] + 1;
        }
    }
    for (int i = n - 2; i >= 0; i--) {
        if (state[i + 1] == 'L' && state[i] == '.') {
            state[i] = 'L';
            stateChangedTime[i] = stateChangedTime[i + 1] + 1;
        }
        if (state[i + 1] == 'L' && state[i] == 'R') {
            if (stateChangedTime[i + 1] + 1 == stateChangedTime[i]) {
                state[i] = '.';
            } else if (stateChangedTime[i + 1] + 1 < stateChangedTime[i]) {
                state[i] = 'L';
                stateChangedTime[i] = stateChangedTime[i + 1] + 1;
            }
        }
    }
    String ans = new String(state);
    System.out.println(ans);
    return ans;
}
