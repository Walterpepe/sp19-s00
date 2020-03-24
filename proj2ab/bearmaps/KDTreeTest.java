package bearmaps;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {

    @Test
    public void sanityGeneralTest(){

        List<Point> l = new LinkedList<>();
        l.add(new Point(5, 4));
        l.add(new Point(2, 8));
        l.add(new Point(2, 8));
        l.add(new Point(0, 5));
        l.add(new Point(1, 3));
        l.add(new Point(5, 7));
        l.add(new Point(1, 7));
        l.add(new Point(9, 9));
        l.add(new Point(8, 6));
        l.add(new Point(5, 1));

        PointSet kd = new KDTree(l);

        assertEquals(new Point(5, 7), kd.nearest(4, 8));
    }

    @Test
    public void sanityRandomTest(){
        int NUM = 1000;
        List<Point> l = new LinkedList<>();
        System.out.println("NODE");
        for( int i =0 ; i < NUM; i++){
            double x = StdRandom.uniform(NUM);
            double y = StdRandom.uniform(NUM);
            System.out.printf("x: %g, y: %g\n", x, y);
            l.add(new Point(x, y));
        }
        PointSet kd = new KDTree(l);
        PointSet nn  = new NaivePointSet(l);

        int QUERY = 100;
        System.out.println("QUERY");
        for( int i =0; i < QUERY; i++){
            double x = StdRandom.uniform(NUM);
            double y = StdRandom.uniform(NUM);
            System.out.printf("x: %g, y: %g\n", x, y);

            assertEquals(nn.nearest(x, y), kd.nearest(x, y));
        }
    }

    @Test
    public void sanityStopWatchKDTest(){
        Stopwatch timer = new Stopwatch();
        int NUM = 1000000;
        List<Point> l = new LinkedList<>();
        for( int i =0 ; i < NUM; i++){
            double x = StdRandom.uniform(NUM);
            double y = StdRandom.uniform(NUM);
            l.add(new Point(x, y));
        }
        PointSet kd = new KDTree(l);

        int QUERY = 100000;
        for( int i =0; i < QUERY; i++){
            double x = StdRandom.uniform(NUM);
            double y = StdRandom.uniform(NUM);
        }
        StdOut.printf("elapse (%.2f seconds)\n", timer.elapsedTime());
    }

    @Test
    public void sanityStopWatchNaiveTest(){
        Stopwatch timer = new Stopwatch();
        int NUM = 1000000;
        List<Point> l = new LinkedList<>();
        for( int i =0 ; i < NUM; i++){
            double x = StdRandom.uniform(NUM);
            double y = StdRandom.uniform(NUM);
            l.add(new Point(x, y));
        }
        PointSet kd = new NaivePointSet(l);

        int QUERY = 100000;
        for( int i =0; i < QUERY; i++){
            double x = StdRandom.uniform(NUM);
            double y = StdRandom.uniform(NUM);
        }
        StdOut.printf("elapse (%.2f seconds)\n", timer.elapsedTime());
    }

}
