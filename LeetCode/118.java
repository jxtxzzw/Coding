class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> last = null;
        for (int i = 1; i <= numRows; i++) {
            ArrayList<Integer> al = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == i - 1) {
                    al.add(1);
                } else {
                    assert last != null;
                    al.add(last.get(j - 1) + last.get(j));
                }
            }
            last = al;
            ans.add(al);
        }
        return ans;
    }
}