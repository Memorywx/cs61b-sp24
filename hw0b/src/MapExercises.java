import net.sf.saxon.expr.Component;

import java.util.*;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        // TODO: Fill in this function.
        Map<Character, Integer> M = new TreeMap<>();
        for (int i = 0; i < 26; i++){
            M.put((char)('a' + i), i+1);
        }
        return M;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        // TODO: Fill in this function.
        Map<Integer, Integer> sq = new TreeMap<>();
        for (int i : nums){
            sq.put(i, i*i);
        }
        return sq;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        // TODO: Fill in this function.
        Map<String, Integer> co = new TreeMap<>();
        for (String i : words){
            if ( co.containsKey(i) ){
                co.put(i, co.get(i) + 1);
            }else {
                co.put(i, 1);
            }
        }
        return co;
    }
}
