public List<Integer> grayCode(int n) {
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(0);
        ans.add(1);
        for (int i = 2; i <= n; i++) {
            int len = ans.size();
            // step 1: 每个元素二进制形式前面添加 0
            // step 2: 倒序每个元素二进制形式前面添加 1
            // step 3: 拼接两个集合即可得到下一阶格雷码
            for (int j = len - 1; j >= 0; j--) {
                ans.add((1 << (i - 1)) + ans.get(j));
            }
        }
        return ans;
    }