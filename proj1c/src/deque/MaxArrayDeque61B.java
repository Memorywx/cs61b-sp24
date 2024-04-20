package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {

    public MaxArrayDeque61B(Comparator<T> c) {
        MaxArrayDeque61B<T> m = new MaxArrayDeque61B<>(c);
    }

    public T max() {
        if ( this == null) {
            return null;
        }
    }


}
