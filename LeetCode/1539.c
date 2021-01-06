int findKthPositive(int* arr, int arrSize, int k){
    int t = 1;
    int i = 0;
    while (k != 0) {
        if (i < arrSize) {
            if (arr[i] != t) {
                k--;    
            } else {
                i++;
            }
        } else {
            k--;
        }
        t++;
    }
    return t - 1;
}