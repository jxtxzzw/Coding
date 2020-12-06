class Solution {
    public Node connect(Node root) {
        if (root != null) {
            Node leftmost = root;
            while (leftmost != null) {
                Node from = null;
                Node nextLeftmost = null;
                for (Node cursor = leftmost; cursor != null; cursor = cursor.next) {
                    Node to = cursor.left;
                    if (to != null) {
                        if (from != null) {
                            from.next = to;
                        }
                        from = to;
                        if (nextLeftmost == null) {
                            nextLeftmost = to;
                        }
                    }
                    to = cursor.right;
                    if (to != null) {
                        if (from != null) {
                            from.next = to;
                        }
                        from = to;
                        if (nextLeftmost == null) {
                            nextLeftmost = to;
                        }
                    }
                }
                leftmost = nextLeftmost;
            }
        }
        return root;
    }
}
