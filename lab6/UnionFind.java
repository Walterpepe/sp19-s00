import java.util.List;
import java.util.ArrayList;

public class UnionFind {

    // Add instance variables?
    private int[] parent;
    private int count;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parent = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    public int count() {
        return count;
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        int n = parent.length;
        if (0 < vertex || vertex >= n) {
            throw new IllegalArgumentException("index " + vertex + " is not between 0 and " + (n - 1));
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        return -parent[find(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int i = find(v1);
        int j = find(v2);

        if (i == j) return;

        // size 是负数
        if (parent[i] < parent[j]) {
            int size = parent[j];
            parent[j] = i;
            parent[i] += size;

        } else {
            int size = parent[i];
            parent[i] = j;
            parent[j] += size;
        }
        count--;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {

        List<Integer> alongPath = new ArrayList<>();

        while (parent[vertex] >= 0) {
            alongPath.add(vertex);
            vertex = parent[vertex];
        }

        if (alongPath.size() == 1){
            return vertex;
        }

        for (Integer item : alongPath) {
            parent[item] = vertex;
        }

        return vertex;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        UnionFind uf = new UnionFind(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }

}
