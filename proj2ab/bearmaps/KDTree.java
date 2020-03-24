package bearmaps;

import edu.princeton.cs.algs4.BST;

import java.util.LinkedList;
import java.util.List;

public class KDTree implements PointSet {
    private Node root;             // root of BST

    private class Node implements Comparable<Point> {
        private Point point;        // sorted by key
        private Node left, right;   // left and right subtrees
        private int depth;          // depth of node

        public Node(Point point, int depth) {
            this.point = point;
            this.depth = depth;
        }

        @Override
        public int compareTo(Point point) {
            if (depth % 2 == 1) {
                return Double.compare(this.point.getY(), point.getY());
            } else {
                return Double.compare(this.point.getX(), point.getX());
            }
        }
    }

    /**
     * You can assume points has at least size 1.
     *
     * @param points
     */
    public KDTree(List<Point> points) {
        for (Point point : points) {
            put(point);
        }
    }

    /**
     * @param point the point
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Point point) {
        if (point == null) throw new IllegalArgumentException("calls put() with a null key");

        root = put(root, point, 0);
    }

    private Node put(Node x, Point point, Integer depth) {
        if (x == null) return new Node(point, depth);
        if (x.point.equals(point)) return x;

        int cmp;
        if (depth % 2 == 1) {
            cmp = Double.compare(point.getY(), x.point.getY());
        } else {
            cmp = Double.compare(point.getX(), x.point.getX());
        }

        if (cmp < 0) x.left = put(x.left, point, ++depth);
        else x.right = put(x.right, point, ++depth);

        return x;
    }


    /**
     * Returns the closest point to the inputted coordinates. This should take O(logN)
     * time on average, where N is the number of points.
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    public Point nearest(double x, double y) {
        return nearest(root, new Point(x, y), root).point;
    }

    /**
     * Nearest is a helper method that returns whichever is closer to goal
     * out of the following two choices:
     * 1. best
     * 2. all items in the subtree starting at n
     *
     * @param n
     * @param goal
     * @param best
     * @return
     */
    private Node nearest(Node n, Point goal, Node best) {
        if (n == null) return best;
        if (Point.distance(n.point, goal) < Point.distance(best.point, goal)) best = n;
        Node goodSide, badSide;
        if (n.compareTo(goal) > 0) {
            goodSide = n.left;
            badSide = n.right;
        } else {
            goodSide = n.right;
            badSide = n.left;
        }
        best = nearest(goodSide, goal, best);

        // use the simple way
        if ((n.depth % 2 == 1 && Point.distance(best.point, goal) > Math.pow(n.point.getY() - goal.getY(), 2))
                || (Point.distance(best.point, goal) > Math.pow(n.point.getX() - goal.getX(), 2))) {
            best = nearest(badSide, goal, best);
        }
        return best;

    }


    public static void main(String[] args) {
        Point A = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point Z = new Point(4, 2);
        Point B = new Point(4, 2);
        Point C = new Point(4, 5);
        Point D = new Point(3, 3);
        Point E = new Point(1, 5);
        Point F = new Point(4, 4);

        List<Point> l = new LinkedList<>();
        l.add(A);
        l.add(Z);
        l.add(B);
        l.add(C);
        l.add(D);
        l.add(E);
        l.add(F);

        PointSet nn = new KDTree(l);
        Point ret = nn.nearest(0, 7); // returns p2
        System.out.println(ret.getX()); // evaluates to 3.3
        System.out.println(ret.getY()); // evaluates to 4.4
    }


}
