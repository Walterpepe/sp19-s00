/**
 * Created by hug.
 */
public class ExperimentHelper {

    /**
     * Returns the internal path length for an optimum binary search tree of
     * size N. Examples:
     * N = 1, OIPL: 0
     * N = 2, OIPL: 1
     * N = 3, OIPL: 2
     * N = 4, OIPL: 4
     * N = 5, OIPL: 6
     * N = 6, OIPL: 8
     * N = 7, OIPL: 10
     * N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {

        int count = 0;
        int height = (int) (Math.log(N) / Math.log(2));
        int oipl = 0;
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= Math.pow(2, i); j++) {
                oipl += i;
                if (++count == N - 1) {
                    break;
                }
            }
        }

        return oipl;
    }

    /**
     * Returns the average depth for nodes in an optimal BST of
     * size N.
     * Examples:
     * N = 1, OAD: 0
     * N = 5, OAD: 1.2
     * N = 8, OAD: 1.625
     *
     * @return
     */
    public static double optimalAverageDepth(int N) {
        return optimalIPL(N) / (double) N;
    }


    public static void asymmetric(BST<String> bst) {
        bst.deleteTakingSuccessor(bst.getRandomKey());
        bst.add(StringUtils.randomString(5));
    }

    public static void symmetric(BST<String> bst) {
        bst.deleteTakingRandom(bst.getRandomKey());
        bst.add(StringUtils.randomString(5));
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 8; i++) {
            System.out.printf("N = %d, OIPL: %d\n", i, optimalIPL(i));
        }
    }

}
