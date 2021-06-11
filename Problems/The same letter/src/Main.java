import java.util.*;


class MapFunctions {

    public static void printWithSameLetter(Map<String, String> map) {
        map.entrySet()
                .stream()
                .filter(m -> m.getKey().substring(0,1).equals(m.getValue().substring(0,1)))
                .forEach(m -> System.out.println(m.getKey() + " " + m.getValue()));


    }
}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> map = new HashMap<>();

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] pair = s.split(" ");
            map.put(pair[0], pair[1]);
        }

        MapFunctions.printWithSameLetter(map);
    }
}