class VersionControl {
    int bad;

    VersionControl(int bad) {
        this.bad = bad;
    }

    public boolean isBadVersion(int v) {
        return v >= bad;
    }
}

class Solution extends VersionControl {

    Solution(int bad) {
        super(bad);
    }

    public int firstBadVersion(int n) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >>> 1);
            if (isBadVersion(mid)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}

public class OJ {
    public static void main(String[] args) {
        int n = 5, bad = 4;
        Solution sl = new Solution(bad);
        assert sl.firstBadVersion(n) == bad;
    }
}
