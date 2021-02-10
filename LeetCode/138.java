/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

/*
   Hint #1  
Just iterate the linked list and create copies of the nodes on the go. Since a node can be referenced from multiple nodes due to the random pointers, make sure you are not making multiple copies of the same node.

   Hint #2  
You may want to use extra space to keep old node ---> new node mapping to prevent creating multiples copies of same node.

   Hint #3  
We can avoid using extra space for old node ---> new node mapping, by tweaking the original linked list. Simply interweave the nodes of the old and copied list. For e.g.
Old List: A --> B --> C --> D
InterWeaved List: A --> A' --> B --> B' --> C --> C' --> D --> D'

   Hint #4  
The interweaving is done using next pointers and we can make use of interweaved structure to get the correct reference nodes for random pointers.
*/

class Solution {
    public Node copyRandomList(Node head) {
        Node cursor = head;
        while (cursor != null) {
            Node node = new Node(cursor.val);
            node.next = cursor.next;
            cursor.next = node;
            cursor = node.next;
        }
        cursor = head;
        while (cursor != null) {
            cursor.next.random = cursor.random == null ? null : cursor.random.next;
            cursor = cursor.next.next;
        }
        cursor = head;
        Node newHead = head == null ? null : head.next;
        while (cursor != null) {
            Node oldNext = cursor.next.next;
            cursor.next.next = oldNext == null ? null : oldNext.next;
            cursor.next = oldNext;
            cursor = oldNext;
        }
        return newHead;
    }
}
