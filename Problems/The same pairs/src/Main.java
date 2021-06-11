import java.util.*;


class MapFunctions {

    public static void calcTheSamePairs(Map<String, String> map1, Map<String, String> map2) {

        long count = map1.entrySet()
                .stream()
                .filter(m1 -> map2.containsKey(m1.getKey()) && map2.get(m1.getKey()).equals(m1.getValue()))
                .count();

        System.out.println(count);

    }
}