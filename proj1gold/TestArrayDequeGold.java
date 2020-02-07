import static org.junit.Assert.*;

import org.junit.Test;

//import java.util.Arrays;

public class TestArrayDequeGold {

    public static String printArray(String[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == null) {
                break;
            }

            sb.append("\n");

            sb.append(arr[i]);
        }
        return sb.toString();
    }

    @Test
    public void TestWithRandom() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();

        int N = 1000;

        Integer expected = 0;
        Integer actual = 0;

        String[] actions = new String[N];

        for (int i = 0; i < N; i += 1) {

            switch (StdRandom.uniform(4)) {
                case 0:
                    sad.addFirst(i);
                    ads.addFirst(i);
                    actions[i] = "addFirst(" + i + ")";
                    break;
                case 1:
                    sad.addLast(i);
                    ads.addLast(i);
                    actions[i] = "addLast(" + i + ")";
                    break;
                case 2:
                    if (!sad.isEmpty()) {
                        actual = sad.removeFirst();
                    }
                    if (!ads.isEmpty()) {
                        expected = ads.removeFirst();
                    }
                    actions[i] = "removeFirst()";

                    assertEquals("actions: " + printArray(actions),
                            expected, actual);

                    break;
                case 3:
                    if (!sad.isEmpty()) {
                        actual = sad.removeLast();
                    }
                    if (!ads.isEmpty()) {
                        expected = ads.removeLast();
                    }

                    actions[i] = "removeLast()";

                    assertEquals("actions: " + printArray(actions),
                            expected, actual);

                    break;
            }
        }
    }


}


