import org.junit.Test;

import static org.junit.Assert.*;

public class TestBST {

    @Test
    public void testIPL() {
        int N = 10;
        int L = 2;

        BST<String> mapBST = new BST<>();

        for (int i = 0; i < N; i++) {
            String str = StringUtils.randomString(L);
            mapBST.add(str);
            System.out.println(str + " => " + mapBST.averageDepth());
        }

        System.out.println(mapBST.size());
        System.out.println("end");
    }
}
