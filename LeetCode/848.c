char * shiftingLetters(char * s, int* shifts, int shiftsSize){
    int * prefix = (int*)malloc(sizeof(int) * (shiftsSize + 1));
    prefix[0] = 0;
    for (int i = 1; i <= shiftsSize; i++) {
        prefix[i] = prefix[i - 1] + (shifts[i - 1] % 26);
    }
    for (int i = 0; i <= shiftsSize; i++) {
        printf("%d ", prefix[i]);
    }
    for (int i = 0; i < shiftsSize; i++) {
        s[i] = (s[i] - 'a' + (prefix[shiftsSize] - prefix[i])) % 26 + 'a';
    }
    return s;
}
