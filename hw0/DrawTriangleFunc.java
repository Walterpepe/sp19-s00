public class DrawTriangleFunc {

    public static void drawTriangle(int N){
        int SIZE = N;

        int row = 0;
        while (row < SIZE) {
            int col = 0;
            while (col <= row) {
                System.out.print('*');
                col = col + 1;
            }
            System.out.println();
            row = row + 1;
        }

    }

    public static void main(String[] args ){
        int N = 10;
        drawTriangle(N);
    }
}