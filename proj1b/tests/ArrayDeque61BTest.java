import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }

    @Test
    public void add_first_from_empty(){
        Deque61B<Integer> L = new ArrayDeque61B<>();
        L.addFirst(6); // [6]
        assertThat(L.toList()).containsExactly(6).inOrder();
    }

    @Test
    public void add_first_full_size(){
        Deque61B<Integer> L = new ArrayDeque61B<>();
        L.addFirst(6); // [6]
        L.addFirst(9); // [6 ...  9]
        L.addLast(8);  // [6, 8, ... 9]
        L.addLast(1);  // [6, 8, 1, ..., 9]
        L.addLast(3);  // [6, 8, 1, 3, ..., 9]
        L.addFirst(5); // [6, 8, 1, 3, ..., 5, 9]
        L.addFirst(2); // [6, 8, 1, 3, ..., 2, 5, 9]
        L.addFirst(7); // [6, 8, 1, 3, 7, 2, 5, 9]
        L.addLast(6);
//        L.addFirst(10);
//        L.addLast(99);
//        L.addFirst(88);
//        L.addFirst(77);
//        L.addFirst(66);
//        L.addLast(55);
//        L.addFirst(44);
//        L.addLast(33);
        assertThat(L.toList()).containsExactly(7, 2, 5, 9, 6, 8, 1, 3, 6).inOrder();
//        assertThat(L.toList()).containsExactly(44,66,77,88,10,7, 2, 5, 9, 6, 8, 1, 3, 6, 99,55,33).inOrder();
    }

    @Test void remove_test(){
        Deque61B<Integer> L = new ArrayDeque61B<>();
        L.addFirst(6); // [6]
        L.addFirst(9); // [6 ...  9]
        L.addLast(8);  // [6, 8, ... 9]
        L.addLast(1);  // [6, 8, 1, ..., 9]
        L.addLast(3);  // [6, 8, 1, 3, ..., 9]
        L.addFirst(5); // [6, 8, 1, 3, ..., 5, 9]
        L.addFirst(2); // [6, 8, 1, 3, ..., 2, 5, 9]
        L.addFirst(7); // [6, 8, 1, 3, 7, 2, 5, 9]
        L.addLast(9);

        L.removeFirst();
        L.removeLast();
        L.removeLast();
        L.removeLast();
        L.removeLast();
        L.removeLast();

       assertThat(L.toList()).containsExactly(6, 8, null, null, null, 2, 5, 9).inOrder();
    }

}
