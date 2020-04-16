bool backspaceCompare(char * S, char * T){
    int i = strlen(S) - 1;
    int j = strlen(T) - 1;
    int skipS = 0;
    int skipT = 0;
    while (i >= 0 || j >= 0) {
        while (i >= 0 && S[i] == '#') {
            while (i >= 0 && S[i] == '#') {
                skipS++;
                i--;
            }
            while (i >= 0 && skipS > 0) {
                if (S[i] == '#') {
                    skipS++;
                } else {
                    skipS--;
                }
                i--;
            }
        }
        while (j >= 0 && T[j] == '#') {
            while (j >= 0 && T[j] == '#') {
                skipT++;
                j--;
            }
            while (j >= 0 && skipT > 0) {
                if (T[j] == '#') {
                    skipT++;
                } else {
                    skipT--;
                }
                j--;
            }
        }
        if (i >=0 && j >= 0) {
            if (S[i] != T[j]) {
                return false;
            } else {
                i--;
                j--;
            }
        } else {
            return i < 0 && j < 0;
        }
    }
    return true;
}