import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        int totalNum = 0;
        if (L.isEmpty()){
            return 0;
        }else {
            for (int i : L){
                totalNum += i;
            }
        }
        return totalNum;

    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        List<Integer> Even = new ArrayList<>();
        for (int i : L){
            if (i % 2 == 0){
                Even.add(i);
            }
        }
        return Even;

    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        List<Integer> commonList = new ArrayList<>();
        for (int i : L1){
            if (L2.contains(i)){
                commonList.add(i);
            }
        }
        return commonList;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        int sumC = 0;
        for (String str : words){
            for (int j = 0; j < str.length(); j++){
                if(c == str.charAt(j)){
                    sumC++;
                }
            }
        }
        return sumC;
    }
}
