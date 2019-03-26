/**
 * Design Linked List        -- 自定义链表集合
 *
 * @Author Sun
 * @date 2019-03-26
 */
public class DesignLinkedList {

    /**
     * Design your implementation of the linked list. You can choose to use the singly linked list or the doubly linked list.
     * A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next
     * is a pointer/reference to the next node. If you want to use the doubly linked list, you will need one more attribute prev
     * to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.
     *
     * Implement these functions in your linked list class:
     *      get(index) : Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     *      addAtHead(val) : Add a node of value val before the first element of the linked list. After the insertion,
     *                       the new node will be the first node of the linked list.
     *      addAtTail(val) : Append a node of value val to the last element of the linked list.
     *      addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list. If index equals to the length
     *                               of linked list, the node will be appended to the end of linked list. If index is greater than the length,
     *                               the node will not be inserted.
     *      deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.
     *
     * Example:
     *      MyLinkedList linkedList = new MyLinkedList();
     *      linkedList.addAtHead(1);
     *      linkedList.addAtTail(3);
     *      linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
     *      linkedList.get(1);            // returns 2
     *      linkedList.deleteAtIndex(1);  // now the linked list is 1->3
     *      linkedList.get(1);            // returns 3
     *
     * Note:
     *      1. All values will be in the range of [1, 1000].
     *      2. The number of operations will be in the range of [1, 1000].
     *      3. Please do not use the built-in LinkedList library.
     */


    public static void main(String... args) {
        // Your MyLinkedList object will be instantiated and called as such:
        DesignLinkedList obj = new DesignLinkedList();
        int param_1 = obj.get(1);
        obj.addAtHead(3);
        obj.addAtTail(2);
        obj.addAtIndex(1, 3);
        obj.deleteAtIndex(2);
    }


    public class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    Node head;
    Node tail;
    int size;

    /**
     * Initialize your data structure here.
     */
    public DesignLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (size == 0 || index >= size) {
            return -1;
        }
        Node cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        Node newNode = new Node(val);
        newNode.next = head;
        head = newNode;
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        Node newNode = new Node(val);
        if (tail != null) {
            tail.next = newNode;
        } else {
            head = newNode;
        }
        tail = newNode;
        size++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index == 0) {
            addAtHead(val);
            return;
        }
        if (index == size) {
            addAtTail(val);
            return;
        }
        Node newNode = new Node(val);
        Node cur = head;
        for (int i = 0; i < index - 1; i++) {
            cur = cur.next;
        }
        newNode.next = cur.next;
        cur.next = newNode;
        size++;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (size == 0 || index >= size) {
            return;
        }
        Node cur = head;
        if (index == 0) {
            if (size == 1) {
                head = null;
                tail = null;
                size = 0;
                return;
            }
            head = head.next;
            cur.next = null;
            size--;
            return;
        }
        for (int i = 0; i < index - 1; i++) {
            cur = cur.next;
        }
        if (index == size - 1) {
            tail = cur;
            tail.next = null;
            size--;
            return;
        } else {
            cur.next = cur.next.next;
        }
        size--;
    }

}
