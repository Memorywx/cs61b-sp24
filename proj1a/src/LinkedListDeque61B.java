import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    public class Node{
        T item;
        Node prev;
        Node next;

        public Node(Node f, T i, Node n){
            item = i;
            prev = f;
            next = n;
        }

    }

    public Node sentinel;
    public int size;


    public LinkedListDeque61B(){
        sentinel = new Node(null, null, null);
        // 不能直接 sentinel = new Node(sentinel, null, sentinel);
        // 因为此时 sentinel还没保存地址，为null。
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

    }

    @Override
    public void addFirst(T x) {
        Node firstNode = new Node(sentinel, x, sentinel.next);
        sentinel.next.prev = firstNode;
        sentinel.next = firstNode;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node lastNode = new Node(sentinel.prev, x, sentinel);
        sentinel.prev.next = lastNode;
        sentinel.prev = lastNode;
        size++;

    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node p = sentinel;
        while (p.next != sentinel){
            p = p.next;
            returnList.add(p.item);
        }

        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.next == sentinel){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T removedFirstItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.next.prev = sentinel;
        size--;
        return removedFirstItem;  // 返回被删除掉的LinkedList元素
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        T removedLastItem = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return removedLastItem;

    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size)
            return null;

        Node p = sentinel.next;
        int i = 0;
        while (i != index){
            p = p.next;
            i++;
        }
        return p.item;

    }

    @Override
    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel, index);
    }


    // 因为Node类不具备getRecursive方法, 因此得有个帮手。
    public T getRecursiveHelper(Node p, int index){
        if (index < 0 || index >= size) {
            return null;
        }

        if (index == 0){
            return p.next.item;     // return getRecursiveHelper(sentinel, index);
        }                           // 由于是由sentinel开始的，所以需要写 p.next.item
                                    // 若是从sentinel.next开始，直接写 p.item 就好了

        return getRecursiveHelper(p.next, index - 1);
    }


    public static void main(String[] args) {
        LinkedListDeque61B<Integer> L = new LinkedListDeque61B<>();
        L.addFirst(5);
        L.addFirst(6);
        LinkedListDeque61B<Integer> p = new LinkedListDeque61B<>();
        p.addLast(9);
        p.addLast(7);
        p.addFirst(4);
    }
}
