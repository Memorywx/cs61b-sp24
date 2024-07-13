

import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T>{

    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;
    private final int initialLength = 8;

    private final double LessenFactor = 0.25;
    private final double ExpandFactor = 0.75;

    public ArrayDeque61B() {
        items = (T[]) new Object[initialLength];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }
    private void whetherResize() {
        if (size() == items.length) {   // 8 -> 16 的情况
            resize(items.length * 2);
        }
        double proportion = (double) this.size / items.length;
        if (this.items.length >= 16) {
            if (proportion >= ExpandFactor) {
                resize(items.length * 2);
            } else if (proportion <= LessenFactor) {
                resize(items.length / 2);
            }
        }
    }

    // 测试方法
    public int getItemsLength() {
        return items.length;
    }

    private void resize(int capability) {
        T[] newItems = (T[]) new Object[capability];
        int index = floorMod(nextFirst+1, items.length);
        for (int i=0; i < this.size; i++) {
            newItems[i] = items[index];
            index = floorMod(index+1, items.length);
        }
        nextFirst = newItems.length - 1;
        nextLast = this.size;
        items = newItems;
    }


    private int floorMod(int v1, int v2) {
        return Math.floorMod(v1, v2);
    }

    @Override
    public void addFirst(T x) {
        whetherResize();
        items[nextFirst] = x;
        nextFirst = floorMod(nextFirst - 1, items.length);
        size++;

    }

    @Override
    public void addLast(T x) {
        whetherResize();
        items[nextLast] = x;
        nextLast = floorMod(nextLast + 1, items.length);
        size++;

    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i=0; i<size; i++) {
            returnList.add(get(i));
        }
        return returnList;
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
        if (isEmpty()) {
            return null;
        }
        nextFirst = floorMod(nextFirst+1, items.length);
        T removedItem = items[nextFirst];
        items[nextFirst] = null;
        size--;
        whetherResize();
        return removedItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast = floorMod(nextLast-1, items.length);
        T removedItem = items[nextLast];
        items[nextLast] = null;
        size--;
        whetherResize();
        return removedItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int returnIndex = floorMod(nextFirst+1+index, items.length);
        return items[returnIndex];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
