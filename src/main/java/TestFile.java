import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;


@Slf4j
@Getter
public class TestFile {

    /*
    * Regarding task, not sure if array should contain unique values.
    * */
    String log4jConfPath = "log4j.properties";

    public void printIt(List<Integer> listOfValues, Integer number) {
        log.info(" ======================= ");
        log.info("Results for number {} in {}", number, listOfValues);

        log.info("Vanilla:\t\t {}", this.vanillaM(listOfValues, number));
        log.info("Streams:\t\t {}", this.streamM(listOfValues, number));
    }

    public static void main(String[] args) {
        TestFile obj = new TestFile();
        PropertyConfigurator.configure(obj.getLog4jConfPath());
        List<Integer> listOfValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);
        obj.printIt(listOfValues, 2);
        obj.printIt(listOfValues, 5);
        listOfValues = Arrays.asList(2, 2, 2, 3, 4, 4, 5, 6, 7, 8);
        obj.printIt(listOfValues, 6);
        obj.printIt(listOfValues, 8);
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
