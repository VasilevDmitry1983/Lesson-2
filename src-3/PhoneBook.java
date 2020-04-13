import java.util.*;

public class PhoneBook {

    public static void main(String[] args) {
        Map<String, String> book = new HashMap<>();
        book.put("123456", "Ivanov");
        book.put("223345", "Suhov");
        book.put("323987", "Petrov");
        book.put("423123", "Ivanov");
        System.out.println(book);
        add(book, "234589", "Hrupin");
        System.out.println(book);
        get(book, "Ivanov");

    }
    public static Map<String, String> add(Map<String, String> book, String phone, String name){
        book.put(phone, name);
        return book;
    }
    public static void get(Map<String, String>book, String name){
        List<String> numbers = new ArrayList<>();
        for (Map.Entry<String, String> i : book.entrySet()) {
            if (i.getValue().equals(name)){
                numbers.add(i.getKey());

            }
        }
        System.out.println(numbers);

    }

}