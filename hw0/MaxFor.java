public class MaxFor {
    /**
     * Returns the maximum value from m using a for loop.
     */
    public static int forMax(int[] m) {
        int max = 0;
        for(int item: m ){
            if(item > max){
                max = item;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.println(forMax(numbers));
    }
}
