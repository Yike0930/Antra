import java.util.ArrayList;
import java.util.List;

public class DoubleLinkedList {
    private volatile static DoubleLinkedNode head;
    private volatile static DoubleLinkedNode tail;

    public DoubleLinkedList() {
        head = new DoubleLinkedNode();
        tail = new DoubleLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    synchronized public void put(DoubleLinkedNode node) {
        if(head.next == tail) {
            head.next = node;
            node.prev = head;
            node.next = tail;
            tail.prev = node;
        } else {
            DoubleLinkedNode dummyNode = head.next;
            while (dummyNode != tail && dummyNode.value > node.value) {
                dummyNode = dummyNode.next;
            }
            dummyNode.prev.next = node;
            node.prev = dummyNode.prev;
            dummyNode.prev = node;
            node.next = dummyNode;
        }
    }

    synchronized public void delete(DoubleLinkedNode node) {
        if(node == head || node == tail) return;
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    synchronized public List<String> getTopN(int n) {
        List<String> res = new ArrayList<>();
        DoubleLinkedNode dummyNode = head.next;
        for(int i = 0; i < n; i++) {
            if(dummyNode == tail) {
                break;
            } else {
                res.add(dummyNode.key);
                dummyNode = dummyNode.next;
            }
        }
        return res;
    }
}
