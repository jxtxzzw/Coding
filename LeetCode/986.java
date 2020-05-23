class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        ArrayList<int[]> list = new ArrayList<>();
        int indexA = 0;
        int indexB = 0;
        while (indexA < A.length && indexB < B.length) {
            int low = Math.max(A[indexA][0], B[indexB][0]);
            int high = Math.min(A[indexA][1], B[indexB][1]);
            if (low <= high) {
                list.add(new int[]{low, high});
            }
            if (A[indexA][1] < B[indexB][1]) {
                indexA++;
            } else {
                indexB++;
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}
