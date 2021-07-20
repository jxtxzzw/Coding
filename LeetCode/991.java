class Solution {
    public int brokenCalc(int X, int Y) {
        int count = 0;
        while (X < Y) {
            count++;
            if (Y % 2 == 0)
                Y /= 2;
            else
                Y += 1;
        }
        count += X - Y;
        return count;
    }
}
