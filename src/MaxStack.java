import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by candy on 1/28/18.
 */
public class MaxStack {
    TreeMap<Integer, List<Node>> map;
    DoubleLinkedList dll;
    /** initialize your data structure here. */
    public MaxStack() {
        map = new TreeMap<>();
        dll = new DoubleLinkedList();
    }

    public void push(int x) {
        Node node = dll.add(x);
        if (!map.containsKey(x)) {
            map.put(x, new ArrayList<>());
        }
        map.get(x).add(node);
    }

    public int pop() {
        int val = dll.pop();
        List<Node> list = map.get(val);
        list.remove(list.size() - 1);
        if (list.size() == 0) {
            map.remove(val);
        }
        return val;
    }

    public int top() {
        return dll.peek();
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        int ans = map.lastKey();
        List<Node> list = map.get(ans);
        Node node = list.remove(list.size() - 1);
        if (list.size() == 0) {
            map.remove(ans);
        }
        dll.unlink(node);
        return ans;
    }

    class DoubleLinkedList {
        Node head;
        Node tail;
        public DoubleLinkedList() {
            head = new Node(0);
            tail = new Node(0);
            head.next = tail;
            tail.prev = head;
        }
        public Node add(int val) {
            Node node = new Node(val);
            node.prev = tail.prev;
            node.next = tail;
            tail.prev.next = node;
            tail.prev = node;
            return node;
        }
        public int pop() {
            return unlink(tail.prev).val;
        }
        public int peek() {
            return tail.prev.val;
        }
        public Node unlink(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            return node;
        }
    }

    class Node {
        int val;
        Node prev;
        Node next;
        public Node(int val) {
            this.val = val;
        }
    }
}
