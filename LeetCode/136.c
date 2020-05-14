int singleNumber(int* nums, int numsSize){
    int xor = 0;
    for (int i = 0; i < numsSize; i++) {
        xor ^= nums[i];
    }
    return xor;
}
