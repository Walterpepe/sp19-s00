package bearmaps;

import java.util.LinkedList;
import java.util.List;

public class NaivePointSet implements PointSet {

    private List<Point> points;

    /**
     * You can assume points has at least size 1.
     *
     * @param points a list of point
     */
    public NaivePointSet(List<Point> points){
        this.points = points;
    }

    /**
     * Returns the closest point to the inputted coordinates. This should take θ(N)
     *  time where N is the number of points.
     *
     * @param x  x
     * @param y  y
     * @return
     */
    @Override
    public Point nearest(double x, double y){
        Point goal = new Point(x, y);
        Point nearestPoint = points.get(0);
        for(Point point : points ){
            if( Point.distance(point,goal) < Point.distance(nearestPoint, goal)){
                nearestPoint = point;
            }
        }

        return nearestPoint;
    }

    public static void main(String[] args){
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        List<Point> l = new LinkedList<>();
        l.add(p1);
        l.add(p2);
        l.add(p3);

        NaivePointSet nn = new NaivePointSet(l);
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        System.out.println(ret.getX()); // evaluates to 3.3
        System.out.println(ret.getY()); // evaluates to 4.4
    }

}
