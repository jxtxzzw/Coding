class Solution {
    public int findComplement(int num) {
        return Integer.parseInt(Integer.toBinaryString(num).replace("0", ".").replace("1","0").replace(".","1"), 2);
    }
}
