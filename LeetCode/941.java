class Solution {
    public boolean validMountainArray(int[] arr) {
        if (arr.length < 3) {
            return false;
        }
        int i = 1;
        while (arr[i - 1] < arr[i]) {
            i++;
            if (i == arr.length) {
                return false;
            }
        }
        if (i == 1) {
            return false;
        }
        while (arr[i - 1] > arr[i]) {
            i++;
            if (i == arr.length) {
                return true;
            }
        }
        return false;
    }
}
