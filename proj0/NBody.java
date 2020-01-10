public class NBody {

    public static double readRadius ( String f ) {
        In in = new In(f);
        in.readInt();

        return  in.readDouble();
    }

    public static  Body[] readBodies ( String f  ){
        In in = new In(f);
        int N = in.readInt();
        in.readDouble();

        int i = 0;
        Body[] bs = new Body[N];
        while( i < N ){
            bs[i] = new Body(in.readDouble(),in.readDouble(),in.readDouble(),
                    in.readDouble(),in.readDouble(),in.readString());
            i++;
        }
        return bs;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);

        double t = 0;
        while ( t < T) {
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];

            for( int i = 0; i < bodies.length; i++){
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }

            for ( int i = 0; i < bodies.length; i++) {
                bodies[i].update(dt, xForces[i],yForces[i]);
            }

            /* 画图 */
            StdDraw.enableDoubleBuffering();
            StdDraw.setScale(-radius, radius);
            StdDraw.clear();

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for ( Body b : bodies) {
                b.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

            t += dt;
        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }

    }
}
