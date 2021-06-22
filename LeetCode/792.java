class Solution {
            public static int numMatchingSubseq(String s, String[] words) {
        // 先把每个单词出现的位置记录下来，之后直接二分查找
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            map.put(c, new ArrayList<>());
        }
        for (int i = 0; i < s.length(); i++) {
            map.get(s.charAt(i)).add(i);
        }

        int ans = 0;
        for (String word : words) {
            int prev = -1;
            boolean possible = true;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                ArrayList<Integer> list = map.get(c);
                int result = Collections.binarySearch(list, prev + 1);
                if (result < 0) {
                    result = - (result + 1);
                }
                if (result >= list.size()) {
                    possible = false;
                    break;
                }
                prev = list.get(result);
            }
            if (possible) {
                ans++;
            }
        }
        return ans;
    }
}
