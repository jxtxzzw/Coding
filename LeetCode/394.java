class Solution {
        public static String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        int searchFrom = s.length() - 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c)) {
                sb.append(c);
            } else if (Character.isDigit(c)) {
                int num = 0;
                while (Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                assert (s.charAt(i) == '[');
                int count = 0;
                int end = i;
                for (int j = i; j < s.length(); j++) {
                    if (s.charAt(j) == '[') {
                        count++;
                    } else if (s.charAt(j) == ']') {
                        count--;
                        if (count == 0) {
                            end = j;
                            break;
                        }
                    }
                }
                assert (end > i);
                String t = decodeString(s.substring(i + 1, end));
                for (int j = 0; j < num; j++) {
                    sb.append(t);
                }
                i = end;
            } else {
                assert false;
            }
        }
        return sb.toString();
    }
}
