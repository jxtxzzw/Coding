class Solution {
    public int reverse(int x) {
        Long n = new Long(new StringBuffer(Long.toString(Math.abs((long)x))).reverse().toString());
        Integer ret = 0;
        if (n > Integer.MAX_VALUE)
            return 0;
        else
            ret = new Integer(n.toString());
        return x < 0 ? -ret : ret;
    }
}