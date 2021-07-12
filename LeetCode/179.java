 public String largestNumber(int[] nums) {
        Integer[] ns = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ns[i] = nums[i];
        }
        Arrays.sort(ns, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String s1 = o1.toString();
                String s2 = o2.toString();
                int l1 = s1.length();
                int l2 = s2.length();
                int l = Math.min(l1, l2);
                for (int i = 0; i < l; i++) {
                    if (s1.charAt(i) != s2.charAt(i)) {
                        return Character.compare(s2.charAt(i), s1.charAt(i));
                    }
                }
                if (l1 == l2) {
                    return 0;
                } else if (l1 > l) { // 111311, 1113
                    int i = l;
                    int j = 0;
                    while (s1.charAt(i) == s2.charAt(j)) {
                        i++;
                        j++;
                        if (i == l1) {
                            i = 0;
                        }
                        if (j == l2) {
                            j = 0;
                        }
                        if (i == l && j == 0) {
                            return 0;
                        }
                    }
                    return Character.compare(s2.charAt(j), s1.charAt(i));
                } else { // 432 43243
                    int i = 0;
                    int j = l;
                    while (s1.charAt(i) == s2.charAt(j)) {
                        i++;
                        j++;
                        if (i == l1) {
                            i = 0;
                        }
                        if (j == l2) {
                            j = 0;
                        }
                        if (i == 0 && j == l) {
                            return 0;
                        }
                    }
                    return Character.compare(s2.charAt(j), s1.charAt(i));
                }
            }
        });

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (ns[i] == 0) {
            i++;
            if (i == ns.length) {
                sb.append(0);
                break;
            }
        }
        while (i < ns.length) {
            sb.append(ns[i]);
            i++;
        }
        System.out.println(sb);
        return sb.toString();
    }