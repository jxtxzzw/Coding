int smallestRepunitDivByK(int K){
    int len = 0;
    int remainder = 0;
    // 如果计算了 K 个余数还没遇到 0，说明有重复数字出现，那会无限循环
    while (len < K) {
        // 每一次的余数都是 * 10 + 1，避免对很高位数的 1 做除法运算
        remainder = (remainder * 10 + 1) % K;
        len++;
        if (remainder == 0) {
            return len;
        }
    }
    return -1;
}
