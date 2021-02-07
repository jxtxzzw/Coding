class Solution {
    public int[] shortestToChar(String s, char c) {
        int len = s.length();
        
        ArrayList<Integer> positions = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == c) {
                positions.add(i);
            }
        }
        System.out.println(positions);
        
        int l = -len;
        int x = 0;
        int r = positions.get(x);
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = Math.min(Math.abs(i - l), Math.abs(r - i));
            if (i == r) {
                l = r;
                x++;
                if (x < positions.size()) {
                    r = positions.get(x);
                }
            }
        }
        return ans;
    }
}
