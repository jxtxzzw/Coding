#define MAXN 100005

int nums[MAXN] = {0};

int calculate(char * s){
    int i = 0;
    int top = 0;
    int sign = 1;
    char last = '+';
    int len = strlen(s);
    while (true) {
        if (i == len) {
            break;
        }
        while (i < len && s[i] == ' ') {
            i++;
        }
        int num = 0;
        while (i < len && '0' <= s[i] && s[i] <= '9') {
            num = num * 10 + (s[i] - '0');
            i++;
        }
        if (last == '*') {
            nums[top - 1] = nums[top - 1] * num;
        } else if (last == '/') {
            nums[top - 1] = nums[top - 1] / num;
        } else if (last == '+') {
            nums[top++] = num;
        }else if (last == '-') {
            nums[top++] = -num;
        }
        while (i < len && s[i] == ' ') {
            i++;
        }
        if (i < len) {
            last = s[i];
            i++;
        }
    }
    int ans = 0;
    for (int i = 0; i < top; i++) {
        ans += nums[i];
    }
    return ans;
}
