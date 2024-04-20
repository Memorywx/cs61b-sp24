package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>{

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

    public class LinkedListDeque61BIterator<T> implements Iterator<T> {
        int wizPos;
        Node p;

        public LinkedListDeque61BIterator() {
            wizPos = 0;
            p = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            T returnItem = (T) p.item;
            p = p.next;
            wizPos++;
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDeque61BIterator<T>();
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }

        if ( o instanceof LinkedListDeque61B<?> otherLD) {  // 检查是否是LinkedListDeque61B
            if ( this.size != otherLD.size) {   // 检查size是否相等
                return false;
            }
            Node p = (Node) otherLD.sentinel.next;
            for ( T x : this) {
                if ( x.equals(p.item) ) {
                    p = p.next;
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;

    }

    @Override
    public String toString() {
        StringBuilder x = new StringBuilder();
        x.append("{ ");

        for ( T i : this) {
            x.append(i.toString());
            x.append(" ");
        }
        x.append("}");
        return x.toString();
    }


    public static void main(String[] args) {
        Deque61B<String> l = new LinkedListDeque61B<>();
        l.addFirst("haha");
        l.addFirst("wuwuwu");
        l.addLast("dadjqajn");
        l.addFirst("woqu");
        l.addLast("666");
        l.removeFirst();
        l.removeLast();
        for (String i : l){
            System.out.println(i);
        }

        Deque61B<Integer> l1 = new LinkedListDeque61B<>();
        l1.addFirst(965);
        l1.addLast(45846);
        l1.addFirst(746);

        Deque61B<Integer> l2 = new LinkedListDeque61B<>();
        l2.addFirst(965);
        l2.addLast(45846);
        l2.addFirst(746);

        System.out.println(l1.equals(l2));
        System.out.println(l);
    }
}