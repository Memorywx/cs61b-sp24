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
    public void add_last_from_empty(){
        Deque61B<Integer> L = new ArrayDeque61B<>();
        L.addLast(6); // [null, 6]
        L.addFirst(1);
        assertThat(L.toList()).containsExactly(1, 6).inOrder();
    }

    @Test
    public void add_first_nonempty(){
        Deque61B<String> L = new ArrayDeque61B<>();
        L.addLast("hah");
        L.addLast("wowowo");
        L.addFirst("lalala");
        assertThat(L.toList()).containsExactly("lalala", "hah", "wowowo").inOrder();
    }

    @Test
    public void add_last_nonempty(){
        Deque61B<String> L = new ArrayDeque61B<>();
        L.addFirst("lalala");
        L.addLast("666");
        L.addLast("apple");
        L.addLast("pink");
        assertThat(L.toList()).containsExactly("lalala", "666", "apple", "pink").inOrder();
    }

    @Test
    public void add_first_trigger_resize(){
        Deque61B<Integer> l = new ArrayDeque61B<>();
        l.addFirst(5);
        l.addLast(7);
        l.addFirst(99);
        l.addLast(88);
        l.addFirst(666);
        l.addLast(777);
        l.addLast(333);
        l.addLast(489); // [5, 7, 88, 777, 333, 489, 666, 99]
        l.addFirst(969);
        assertThat(l.toList()).containsExactly(969,666,99,5,7,88,777,333,489).inOrder();
    }


    @Test
    public void add_last_trigger_resize(){
        ArrayDeque61B<Integer> l = new ArrayDeque61B<>();
        l.addFirst(5);
        l.addLast(7);
        l.addFirst(99);
        l.addLast(88);
        l.addFirst(666);
        l.addLast(777);
        l.addLast(333);
        l.addLast(489); // [5, 7, 88, 777, 333, 489, 666, 99]
        l.addLast(31686);
        assertThat(l.toList()).containsExactly(666, 99, 5, 7, 88, 777, 333, 489, 31686).inOrder();
        assertThat(l.getItemsLength()==16);
    }


    @Test
    public void add_first_after_remove_to_empty() {
        Deque61B<Integer> l = new ArrayDeque61B<>();
        l.addFirst(6);
        l.addLast(9);
        l.addLast(7);
        l.removeFirst();
        l.removeLast();
        l.removeLast();
        l.addFirst(999);
        assertThat(l.toList()).containsExactly(999).inOrder();


    }

    @Test
    public void add_last_after_remove_to_empty() {
        Deque61B<Integer> l = new ArrayDeque61B<>();
        l.addFirst(6);
        l.addLast(9);
        l.addLast(7);
        l.removeLast();
        l.removeLast();
        l.removeLast();
        l.addLast(999);
        assertThat(l.toList()).containsExactly(999).inOrder();


    }

    @Test
    public void remove_first(){
        Deque61B<String> l = new ArrayDeque61B<>();
        l.addFirst("haha");
        l.addLast("666");
        l.removeFirst();
        assertThat(l.toList()).containsExactly("666");
    }

    @Test
    public void remove_last(){
        Deque61B<String> l = new ArrayDeque61B<>();
        l.addFirst("haha");
        l.addLast("666");
        l.removeLast();
        assertThat(l.toList()).containsExactly("haha");
    }



    @Test void remove_test(){
        ArrayDeque61B<Integer> L = new ArrayDeque61B<>();
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

       assertThat(L.toList()).containsExactly(2, 5, 9).inOrder();
       assertThat(L.getItemsLength()==8);
    }


    @Test
    public void get_valid(){
        Deque61B<String> l = new ArrayDeque61B<>();
        l.addFirst("7");
        l.addFirst("999");
        l.addFirst("888");
        assertThat(l.get(2)).isEqualTo("7");
    }

    @Test
    public void get_oob_large(){
        Deque61B<String> l = new ArrayDeque61B<>();
        l.addFirst("haha");
        l.addFirst("888");
        assertThat(l.get(2)).isEqualTo(null);
    }

    @Test
    public void get_oob_neg() {
        Deque61B<Integer> l = new ArrayDeque61B<>();
        l.addFirst(9);
        assertThat(l.get(-1)).isEqualTo(null);
    }
}
