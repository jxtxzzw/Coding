class Solution {
    public String strWithout3a3b(int A, int B) {
        StringBuffer sb = new StringBuffer();
        String next = A > B ? "a" : "b";
        int count = 0;
        
        while (A * B != 0) {
            if (count >= 2) {
                next = next.equals("a") ? "b" : "a";
                count = 0;
            }
            
            if (next.equals("a") && A != 0) {
                sb.append("a");
                A--;
                count++;
                if (A < B) {
                    count++;
                }
            }
            
            if (next.equals("b") && B != 0) {
                sb.append("b");
                B--;
                count++;
                if (A > B) {
                    count++;
                }
            }
            
            
            
            
        }
        
        while (A != 0) {
            sb.append("a");
            A--;
        }
        
        while (B != 0) {
            sb.append("b");
            B--;
        }
        
        return sb.toString();
    }
}
