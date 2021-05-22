class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        ArrayList<String> answers = new ArrayList<>();
        for (String candidate : words) {
            if (candidate.length() != pattern.length()) {
                continue;
            }
            HashMap<Character, Character> w2p = new HashMap<>();
            HashMap<Character, Character> p2w = new HashMap<>();
            boolean possible = true;
            int len = candidate.length();
            for (int i = 0; i < len; i++) {
                char c = candidate.charAt(i);
                char p = pattern.charAt(i);
                if (w2p.containsKey(c) && p2w.containsKey(p)) {
                    if (p != w2p.get(c)) {
                        possible = false;
                        break;
                    }
                } else if (!w2p.containsKey(c) && !p2w.containsKey(p)) {
                    w2p.put(c, p);
                    p2w.put(p, c);
                } else {
                    possible = false;
                    break;
                }
            }
            if (possible) {
                answers.add(candidate);
            }
        }
        return answers;
    }
}
