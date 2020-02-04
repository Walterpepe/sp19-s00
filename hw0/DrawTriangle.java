
public class DrawTriangle {

    public static void main(String[] args ){
        int SIZE = 5;

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
}