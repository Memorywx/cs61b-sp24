import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque61B.


    // 测试有元素的LinkedList
    @Test
    public void isEmptyTestNot(){
         LinkedListDeque61B<Integer> L = new LinkedListDeque61B<>();
         L.addFirst(5);

         assertThat(L.isEmpty()).isFalse();
    }

    // 测试空的LinkedList
    @Test
    public void isEmptyTest(){
        LinkedListDeque61B<Integer> L = new LinkedListDeque61B<>();

        assertThat(L.isEmpty()).isTrue();
    }

    // 测试空的LinkedList的size是否为0
    @Test
    public void size0Test(){
         LinkedListDeque61B<Integer> L = new LinkedListDeque61B<>();

         assertThat(L.size()).isEqualTo(0);
    }


    // 测试有两元素LinkedList的size是否为2
    @Test
    public void size2Test(){
        LinkedListDeque61B<Integer> L = new LinkedListDeque61B<>();
        L.addFirst(5);
        L.addLast(6);

        assertThat(L.size()).isEqualTo(2);
    }

    @Test
    public void getNegativeTest(){
        LinkedListDeque61B<Integer> L = new LinkedListDeque61B<>();

        assertThat(L.get(-1)).isEqualTo(null);
    }


    @Test
    public void geteInvalidTest(){
        LinkedListDeque61B<Integer> L = new LinkedListDeque61B<>();
        L.addLast(5);
        L.addLast(6);

        assertThat(L.getRecursive(2)).isEqualTo(null);
    }

    @Test
    public void getFirstTest(){
        LinkedListDeque61B<Integer> L = new LinkedListDeque61B<>();
        L.addFirst(5);

        assertThat(L.get(0)).isEqualTo(5);
    }

    @Test
    public void getLastTest(){
        LinkedListDeque61B<Integer> L = new LinkedListDeque61B<>();
        L.addFirst(6);
        L.addLast(7);

        assertThat(L.get(1)).isEqualTo(7);
    }

    @Test
    public void getManyTest(){
        LinkedListDeque61B<Integer> L = new LinkedListDeque61B<>();
        L.addLast(7);
        L.addFirst(4);
        L.addFirst(3);
        L.addFirst(6);

        assertThat(L.get(0)).isEqualTo(6);
        assertThat(L.get(1)).isEqualTo(3);
        assertThat(L.get(2)).isEqualTo(4);
        assertThat(L.get(3)).isEqualTo(7);
    }


    // 测试递归版的get!!!!!!!!!!!!!!!!!!!!!!
    @Test
    public void getRecursiveNegativeTest(){
        LinkedListDeque61B<Integer> L = new LinkedListDeque61B<>();

        assertThat(L.getRecursive(-1)).isEqualTo(null);
    }


    @Test
    public void getRecursiveInvalidTest(){
        LinkedListDeque61B<Integer> L = new LinkedListDeque61B<>();
        L.addLast(5);
        L.addLast(6);

        assertThat(L.getRecursive(2)).isEqualTo(null);
    }

    @Test
    public void getRecursiveFirstTest(){
        LinkedListDeque61B<Integer> L = new LinkedListDeque61B<>();
        L.addFirst(5);

        assertThat(L.getRecursive(0)).isEqualTo(5);
    }

    @Test
    public void getRecursiveLastTest(){
        LinkedListDeque61B<Integer> L = new LinkedListDeque61B<>();
        L.addFirst(6);
        L.addLast(7);

        assertThat(L.getRecursive(1)).isEqualTo(7);
    }

    @Test
    public void getRecursiveManyTest(){
        LinkedListDeque61B<Integer> L = new LinkedListDeque61B<>();
        L.addLast(7);
        L.addFirst(4);
        L.addFirst(3);
        L.addFirst(6);

        assertThat(L.getRecursive(0)).isEqualTo(6);
        assertThat(L.getRecursive(1)).isEqualTo(3);
        assertThat(L.getRecursive(2)).isEqualTo(4);
        assertThat(L.getRecursive(3)).isEqualTo(7);
    }

    @Test
    public void remove_first(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst(5);
        lld1.addFirst(6);
        lld1.addFirst(7);

        lld1.removeFirst();

        assertThat(lld1.toList()).containsExactly(6, 5).inOrder();
    }

    @Test
    public void remove_last(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst(5);
        lld1.addFirst(6);
        lld1.addFirst(7);

        lld1.removeLast();

        assertThat(lld1.toList()).containsExactly(7, 6).inOrder();
    }

    @Test
    public void remove_first_to_empty(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst(5);
        lld1.addFirst(6);
        lld1.addFirst(7);

        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();

        assertThat(lld1.toList()).isEmpty();
    }

    @Test
    public void remove_last_to_empty(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst(5);
        lld1.addFirst(6);
        lld1.addFirst(7);

        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();

        assertThat(lld1.toList()).isEmpty();
    }

    @Test
    public void remove_first_to_one(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst(5);
        lld1.addFirst(6);
        lld1.addFirst(7);

        lld1.removeFirst();
        lld1.removeFirst();


        assertThat(lld1.toList()).containsExactly(5);
    }

    @Test
    public void remove_last_to_one(){
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst(5);
        lld1.addFirst(6);
        lld1.addFirst(7);

        lld1.removeLast();
        lld1.removeLast();


        assertThat(lld1.toList()).containsExactly(7);
    }
}