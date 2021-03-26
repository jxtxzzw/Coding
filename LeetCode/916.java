class Solution {
    public List<String> wordSubsets(String[] A, String[] B) {
        ArrayList<String> ans = new ArrayList<>();
        int[] count = new int[26];
        for (String s : B) {
            int[] tmp = new int[26];
            for(int i = 0; i < s.length(); i++) {
                tmp[s.charAt(i) - 'a']++;
            }
            for(int i = 0; i < 26; i++) {
                count[i] = Math.max(count[i], tmp[i]);
            }
        }
        
        for (String s : A) {
            int[] count2 = new int[26];
            for(int i = 0; i < s.length(); i++) {
                count2[s.charAt(i) - 'a']++;
            }
            boolean same = true;
            for(int i = 0; i < 26; i++) {
                if (count2[i] < count[i]) {
                    same = false;
                    break;
                }
            }
            if (same) {
                ans.add(s);
            }
        }
        return ans;
        
    }
    
}
