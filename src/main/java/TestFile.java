import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class TestFile {
    public static void main(String[] args) {
        List<Integer> listOfValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);
        Integer number = 2;
        TestFile obj = new TestFile();
        System.out.println(obj.vanillaM(listOfValues, number));
        System.out.println(obj.streamM(listOfValues, number));
    }

    private ArrayList<List<Integer>> vanillaM(List<Integer> listOfValues, Integer number) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        Collections.sort(listOfValues);
        for (int i = 0; i < listOfValues.size(); i++) {
            for (int j = i + 1; j < listOfValues.size(); j++) {
                if (listOfValues.get(i) + listOfValues.get(j) == number) {
                    List<Integer> res2 = Arrays.asList(listOfValues.get(i), listOfValues.get(j));
                    res.add(res2);
                }
            }
        }
        return res;
    }

    private ArrayList<List<Integer>> streamM(List<Integer> listOfValues, Integer number) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        Collections.sort(listOfValues);
        IntStream
            .range(0, listOfValues.size())
            .forEach(i ->
                IntStream
                    .range(i + 1, listOfValues.size())
                    .filter(j -> listOfValues.get(i) + listOfValues.get(j) == number)
                    .forEach(l -> res.add(Arrays.asList(listOfValues.get(i), listOfValues.get(l))))
            );
        return res;
    }

}
