class Solution {
    public int distributeCandies(int[] candyType) {
        HashSet<Integer> s = new HashSet<>();
        for (int i: candyType) {
            s.add(i);
        }
        return Math.min(s.size(), candyType.length / 2);
    }
}
