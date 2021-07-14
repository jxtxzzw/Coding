
class Solution {
    public String customSortString(String order, String str) {
        Comparator<Character> cmp = new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return Integer.compare(order.indexOf(o1), order.indexOf(o2));
            }
        };
        Character[] cc = new Character[str.length()];
        for (int i = 0; i < str.length(); i++) {
            cc[i] = str.charAt(i);
        }
        Arrays.sort(cc, cmp);
        StringBuilder sb = new StringBuilder();
        for (char c : cc) {
            sb.append(c);
        }
        return sb.toString();
    }
}
