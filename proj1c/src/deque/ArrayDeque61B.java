package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    public ArrayDeque61B(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public void whetherResize(){
        if ( items.length >= 16 && size < items.length * 0.25){
            resize(items.length / 2);
        } else if (size == items.length) {
            resize(size * 2);
        }

    }

    private void resize(int capability){
        T[] newItems = (T[]) new Object[capability];
        int index = Math.floorMod(nextFirst + 1, items.length);
        for (int i=0; i < size; i++) {
            newItems[i] = items[index];
            index = Math.floorMod(index + 1, items.length);
        }
        nextFirst = newItems.length - 1;
        nextLast = size;
        items = newItems;
    }


    @Override
    public void addFirst(T x) {
        whetherResize();
        items[nextFirst] = x;
        size++;
        nextFirst = Math.floorMod(nextFirst - 1, items.length);
    }

    @Override
    public void addLast(T x) {
        whetherResize();
        items[nextLast] = x;
        size++;
        nextLast = Math.floorMod(nextLast + 1, items.length);
    }

    @Override
    public List<T> toList() {
        List<T> L = new ArrayList<>();
        for (int i = 0; i < this.size; i++){
            L.add(this.items[i]);
        }
        return L;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()){
            return null;
        }

        // nextFirst 是从右向左循环移动的，删除first的话, 需要让它向右循环移动一次
        // 专门写一个 deletedIndex，只是为了代码更好读懂 TnT
        int deletedIndex = Math.floorMod(nextFirst + 1, items.length);
        T deletedItem = items[deletedIndex];
        items[deletedIndex] = null;
        nextFirst = Math.floorMod(nextFirst + 1, items.length);
        size--;
        // remove的是否resize应在size--之后判断
        whetherResize();

        return deletedItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        // nextFirst 是从左向右循环移动的，删除last的话, 需要让它向左循环移动一次
        int deletedIndex = Math.floorMod(nextLast - 1, items.length);
        T deletedItem = items[deletedIndex];
        items[deletedIndex] = null;
        nextLast = Math.floorMod(nextLast - 1, items.length);
        size--;
        // remove的是否resize应在size--之后判断
        whetherResize();

        return deletedItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size){
            return null;
        }
        // 举个例子，假设 index 为 0，则返回first的位置的数，即让nextFirst循环向右移动一次
        // index 为其他数的话 则相应让 nextFirst + 1 向右移动 index 次
        int returnIndex = Math.floorMod(nextFirst + 1 + index, items.length);
        return items[returnIndex];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    public void printDeque(){
        for (int i = Math.floorMod(nextFirst+1, items.length) ; i != nextLast; i = Math.floorMod(i+1, items.length)){
            System.out.print(items[i] + " ");
        }
    }

    public class ArrayDeque61BIterator<T> implements Iterator<T>{
        private int wizPos;
        private int length;

        public ArrayDeque61BIterator(){
            wizPos = Math.floorMod(nextFirst + 1, items.length);
            length = 0;
        }
        @Override
        public boolean hasNext() {
            return length < size;
            // 本来是写成 return wizPos != nextLast
            // 但是size等于 8 时，wizPos 正好等于 nextLast，所以什么都不会打印出来
        }

        @Override
        public T next() {
            T returnItem = (T) items[wizPos];
            wizPos = Math.floorMod(wizPos + 1, items.length);
            length++;
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator(){
        return new ArrayDeque61BIterator();
    }

//    @Override
//    public boolean equals(Object o) {
//
//    }

    public static void main(String[] args) {
        Deque61B<String> l = new ArrayDeque61B<>();
        l.addFirst("55");
        l.addFirst("985");
        l.addLast("9999");
        l.addLast("44884");
        l.addLast("9449948");
        l.addFirst("884");
        l.addLast("74898");
        l.addFirst("8888888");
        l.removeLast();
        l.removeFirst();
        for (String i : l){
            System.out.println(i);
        }
    }
}
