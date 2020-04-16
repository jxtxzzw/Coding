import java.util.Scanner;

public class OJ {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        int numbers = in.nextInt();
        SegmentTree st = new SegmentTree(in, numbers);
        int queries = in.nextInt();

        int begin;
        int end;
        for (int i = 0; i < queries; ++i) {
            int command = in.nextInt();
            begin = in.nextInt();
            end = in.nextInt();
            switch (command) {
                case 0:
                    System.out.println(st.query(begin, end, 1));
                    break;
                case 1:
                    st.update(begin, end, 1);
                    break;
            }
        }

    }

}

class SegmentTree {

    private NODE[] data;
    Scanner in;

    public SegmentTree(Scanner in, int length) throws Exception {
        this.in = in;
        data = new NODE[length * 4];
        build(1, 1, length);
    }

    private boolean isPrimarySegment(int left, int right) {
        return left == right;
    }

    public void build(int nodeIndex, int left, int right) throws Exception {

        NODE node;
        if (isPrimarySegment(left, right)) {
            boolean black = in.nextInt() == 1;
            node = new NODE(nodeIndex, left, right, black);
        } else {
            int mid = (left + right) >>> 1;
            build(nodeIndex * 2, left, mid);
            build(nodeIndex * 2 + 1, mid + 1, right);
            node = new NODE(nodeIndex, left, right);
            pushUp(node);
        }
        data[nodeIndex] = node;
    }

    private NODE getLeftChild(NODE node) {
        return data[node.getLeftChildIndex()];
    }

    private NODE getRightChild(NODE node) {
        return data[node.getRightChildIndex()];
    }

    private void pushUp(NODE node) throws Exception {
        NODE leftChild = getLeftChild(node);
        NODE rightChild = getRightChild(node);

        int tmp;

        // 左孩子
        // 左边黑色石头的长度
        tmp = leftChild.getCache(NODE.identifier.LEFT_BLACK);
        if (leftChild.getRange() == tmp) {
            // 如果左孩子全是黑的，就加上右孩子的左边黑色
            tmp += rightChild.getCache(NODE.identifier.LEFT_BLACK);
        }
        node.setCache(NODE.identifier.LEFT_BLACK, tmp);
        // 左边白色石头的长度
        tmp = leftChild.getCache(NODE.identifier.LEFT_WHITE);
        if (leftChild.getRange() == tmp) {
            tmp += rightChild.getCache(NODE.identifier.LEFT_WHITE);
        }
        node.setCache(NODE.identifier.LEFT_WHITE, tmp);

        // 右孩子
        // 右边黑色石头的长度
        tmp = rightChild.getCache(NODE.identifier.RIGHT_BLACK);
        if (rightChild.getRange() == tmp) {
            // 如果左孩子全是黑的，就加上右孩子的左边黑色
            tmp += leftChild.getCache(NODE.identifier.RIGHT_BLACK);
        }
        node.setCache(NODE.identifier.RIGHT_BLACK, tmp);
        // 白色石头的长度
        tmp = rightChild.getCache(NODE.identifier.RIGHT_WHITE);
        if (rightChild.getRange() == tmp) {
            tmp += leftChild.getCache(NODE.identifier.RIGHT_WHITE);
        }
        node.setCache(NODE.identifier.RIGHT_WHITE, tmp);

        // 取最大的，要么是左孩子最大的，要么是右孩子最大的，要么是跨越左右的
        tmp = Math.max(leftChild.getCache(NODE.identifier.MAX_BLACK), rightChild.getCache(NODE.identifier.MAX_BLACK));
        tmp = Math.max(tmp, leftChild.getCache(NODE.identifier.RIGHT_BLACK) + rightChild.getCache(NODE.identifier.LEFT_BLACK));
        node.setCache(NODE.identifier.MAX_BLACK, tmp);
        tmp = Math.max(leftChild.getCache(NODE.identifier.MAX_WHITE), rightChild.getCache(NODE.identifier.MAX_WHITE));
        tmp = Math.max(tmp, leftChild.getCache(NODE.identifier.RIGHT_WHITE) + rightChild.getCache(NODE.identifier.LEFT_WHITE));
        node.setCache(NODE.identifier.MAX_WHITE, tmp);
    }

    public void update(int from, int to, int index) throws Exception {
        NODE node = data[index];
        if (node.isCompletelyCovered(from, to)) {
            node.change();
            return;
        }
        pullDownLazy(node);
        if (to > node.getMid()) {
            update(from, to, node.getRightChildIndex());
        }
        if (from < node.getMid() + 1) {
            update(from, to, node.getLeftChildIndex());
        }
        pushUp(node);
    }

    private void pullDownLazy(NODE node) {
        if (node.shouldChange()) {
            getLeftChild(node).change();
            getRightChild(node).change();
            node.clearChange();
        }
    }

    public int query(int from, int to, int index) throws Exception {
        NODE node = data[index];
        if (node.isCompletelyCovered(from, to)) {
            return node.getCache(NODE.identifier.MAX_BLACK);
        }
        pullDownLazy(node);
        int result = 0; // 查询结果
        if (from <= node.getMid())
            result = Math.max(result, query(from, to, node.getLeftChildIndex()));
        if (to > node.getMid())
            result = Math.max(result, query(from, to, node.getRightChildIndex()));
        if (from <= node.getMid() && to > node.getMid()) {
            int l = Math.min(node.getMid() - from + 1, getLeftChild(node).getCache(NODE.identifier.RIGHT_BLACK));
            int r = Math.min(to - node.getMid(), getRightChild(node).getCache(NODE.identifier.LEFT_BLACK));
            // 坑，注意左右连续黑色块可能会超过下标的范围
            result = Math.max(result, l + r);
        }
        pushUp(node);
        return result;
    }

}

class NODE {
    private int index;
    private int left;
    private int right;
    private int[] cache = new int[6];
    private boolean askToChange = false;

    public enum identifier {LEFT_WHITE, LEFT_BLACK, RIGHT_WHITE, RIGHT_BLACK, MAX_BLACK, MAX_WHITE}

    public NODE(int index, int left, int right) {
        this.index = index;
        this.left = left;
        this.right = right;
    }

    public NODE(int index, int left, int right, boolean black) throws Exception {
        this(index, left, right);
        assert left == right; // 必须在叶子节点
        setCache(identifier.LEFT_BLACK, black ? 1 : 0);
        setCache(identifier.RIGHT_BLACK, black ? 1 : 0);
        setCache(identifier.MAX_BLACK, black ? 1 : 0);
        setCache(identifier.LEFT_WHITE, black ? 0 : 1);
        setCache(identifier.RIGHT_WHITE, black ? 0 : 1);
        setCache(identifier.MAX_WHITE, black ? 0 : 1);
    }

    public int getRange() {
        return right - left + 1;
    }

    public int getMid() {
        return (left + right) >>> 1;
    }

    public boolean isCompletelyCovered(int from, int to) {
        return from <= left && to >= right;
    }

    public int getLeftChildIndex() {
        return index * 2;
    }

    public int getRightChildIndex() {
        return index * 2 + 1;
    }

    public int getCache(identifier idx) throws Exception {
        switch (idx) {
            case LEFT_BLACK:
                return cache[0];
            case LEFT_WHITE:
                return cache[1];
            case RIGHT_BLACK:
                return cache[2];
            case RIGHT_WHITE:
                return cache[3];
            case MAX_BLACK:
                return cache[4];
            case MAX_WHITE:
                return cache[5];
        }
        throw new Exception("Unexpected identifier");
    }

    public void setCache(identifier idx, int value) throws Exception {
        switch (idx) {
            case LEFT_BLACK:
                cache[0] = value;
                return;
            case LEFT_WHITE:
                cache[1] = value;
                return;
            case RIGHT_BLACK:
                cache[2] = value;
                return;
            case RIGHT_WHITE:
                cache[3] = value;
                return;
            case MAX_BLACK:
                cache[4] = value;
                return;
            case MAX_WHITE:
                cache[5] = value;
                return;
        }
        throw new Exception("Unexpected identifier");
    }

    private void setChange() {
        askToChange = !askToChange; // 延迟标记叠加
    }

    public void clearChange() {
        askToChange = false;
    }

    public boolean shouldChange() {
        return askToChange;
    }

    public void change() {
        int tmp;
        tmp = cache[0];
        cache[0] = cache[1];
        cache[1] = tmp;
        tmp = cache[2];
        cache[2] = cache[3];
        cache[3] = tmp;
        tmp = cache[4];
        cache[4] = cache[5];
        cache[5] = tmp;
        setChange();
    }
}