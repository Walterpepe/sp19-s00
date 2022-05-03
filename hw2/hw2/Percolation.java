package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private enum Stat {
        BLOCKED, FULL, OPEN
    }

    private WeightedQuickUnionUF uf;
    private Stat[][] sites;
    private int numOfOpen;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {

        sites = new Stat[N][N];
        numOfOpen = 0;

        // 状态信息
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sites[i][j] = Stat.BLOCKED;
            }
        }

        // 连接信息
        uf = new WeightedQuickUnionUF(N);

    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (sites[row][col] != Stat.BLOCKED) return;

        sites[row][col] = Stat.OPEN;
        numOfOpen++;

        if(row > 0 && Stat){

        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return sites[row][col] == Stat.OPEN;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return sites[row][col] == Stat.FULL;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numOfOpen;
    }

    // does the system percolate?
    public boolean percolates(){


        return false;
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args){

    }

}