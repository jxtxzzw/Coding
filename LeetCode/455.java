class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int c = 0;
        int j = 0;
        for (int i = 0; i < s.length;) {
            if (s[i] >= g[j]) {
                c++;
                j++;
            }
            i++;
            if (j == g.length) {
                break;
            }
        }
        System.out.println(c);
        return c;
    }

}