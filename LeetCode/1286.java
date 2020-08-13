
class CombinationIterator {

    // Key: "sorted"
    private final PriorityQueue<String> cache = new PriorityQueue<>();

    public CombinationIterator(String characters, int combinationLength) {
        int n = characters.length();
        int e = (int) Math.pow(2, n);
        for (int i = 1; i < e; i++) {
            String bitMask = Integer.toBinaryString(i);
            int cnt = 0;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < bitMask.length(); j++) {
                if (bitMask.charAt(j) == '1') {
                    cnt++;
                    sb.append(characters.charAt((n - bitMask.length()) + j));
                }
                // Only with combinationLength "1"(s)
                if (cnt > combinationLength) {
                    break;
                }
            }
            if (cnt != combinationLength) {
                continue;
            }
            cache.add(sb.toString());
        }
    }

    public String next() {
        return cache.poll();
    }

    public boolean hasNext() {
        return !cache.isEmpty();
    }
}