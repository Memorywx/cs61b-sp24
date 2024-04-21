package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {

    private Comparator<T> my_c;

    public static class stringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    public MaxArrayDeque61B(Comparator<T> c) {
        my_c = c;
    }


    public T max() {
        if ( this.isEmpty() ) {
            return null;
        }

        T maxItem = this.get(0);
        for ( T x : this ) {
            if ( my_c.compare(x, maxItem ) > 0 ) {
                maxItem = x;
            }
        }
        return maxItem;
    }

    public T max(Comparator<T> c) {
        if ( this.isEmpty() ) {
            return null;
        }

        T maxItem = this.get(0);
        for ( T x : this ) {
            if ( c.compare(x, maxItem) > 0 ) {
                maxItem = x;
            }
        }
        return maxItem;
    }


    public static void main(String[] args) {
        MaxArrayDeque61B<Integer> m = new MaxArrayDeque61B<Integer>(Comparator.naturalOrder());
        m.addFirst(999);
        m.addFirst(65926);
        m.addLast(78);
        m.addLast(8898797);
        System.out.println(m.max(Comparator.naturalOrder()));

        MaxArrayDeque61B<String> l = new MaxArrayDeque61B<>(new stringComparator());
        l.addLast("asdjhiq");
        l.addFirst("pqwje");
        l.addLast("zoa");
        l.addLast("zzz");
        System.out.println(l.max());

    }
}
