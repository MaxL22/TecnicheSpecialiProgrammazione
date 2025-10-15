import java.util.Arrays;
import java.util.stream.Collectors;

public class Test0 {
    private Integer[] magicNumbers = {0, 1, 2};

    public void change() {
        this.magicNumbers[1] = 0;
    };

    private static String arrToStr(Integer[] arr) {
        return Arrays.stream(arr)
               .map((Integer i) -> i.toString())
               .collect(Collectors.joining(", ", "[", "]"));
    }

    @Override
    public String toString() {
        return "magicNumbers: " + arrToStr(this.magicNumbers) + "\n";
    }
}
