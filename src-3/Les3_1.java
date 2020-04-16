import java.util.*;

public class Les3_1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("apple", "orange", "nut", "lemon", "banana", "kiwi", "apricot",
                "apple", "avocado", "broccoli", "apple","nut", "kiwi"));
        Set<String> set = new HashSet<>();
        set.addAll(list);
        List<String > list1 = new ArrayList<>();
        list1.addAll(set);
        System.out.println(list1);
        for (int i = 0; i<list1.size(); i++){
            String w = list1.get(i);
            matchSearch(w, list);
        }
    }

    private static void matchSearch(String wordSample, List<String> list){
        Iterator<String> listIterator = list.listIterator();
        int num = 0;
        while (listIterator.hasNext()){
            String word = listIterator.next();
            if (word.equals(wordSample)) {
                num++;
            }
        }
        System.out.println(wordSample + " : " + num);
    }
}
