public class BubbleGrid {

    private UnionFind grids;


    public BubbleGrid(int[][] grids) {

        int row = grids.length;
        int column = grids[0].length;

        // 多带一个顶层节点
        this.grids = new UnionFind(row * column + 1);

        // topmost row stuck
        for (int j = 0; j <= column; j++){
            if (grids[0][j] == 1){
                this.grids.union(j + 1 , 0);
            }
        }

    }

    private validAdjacent


    public int[] popBubbles(int[][] darts) {

        return null;
    }


}
