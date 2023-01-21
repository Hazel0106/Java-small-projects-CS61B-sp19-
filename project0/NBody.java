package project0;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdAudio;
import edu.princeton.cs.introcs.StdDraw;

public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String fileName) {
        In in = new In(fileName);
        int bodyAmount = in.readInt();
        Body[] bodies = new Body[bodyAmount];
        in.readDouble();
        for (int i = 0; i < bodyAmount; i++) {
            bodies[i] = new Body(in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readDouble(), in.readString());
        }
        return bodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];

        double radius = readRadius(fileName);
        Body[] bodies = readBodies(fileName);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);

        Double[] xForces = new Double[bodies.length];
        Double[] yForces = new Double[bodies.length];

        /* Play the theme to 2001 audio. */
        StdAudio.play("audio/2001.mid");

        Double currentTime = 0.0;
        while (currentTime < T) {
            /* Calculate the net force of each Body. */
            for (int i = 0; i < bodies.length; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            /* Update each Body's position, vel & acceleration. */
            for (int i = 0; i < bodies.length; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.clear();
            /* Background drawing */
            String background = "images/starfield.jpg";
            StdDraw.picture(0, 0, background);
            /* All Body drawing */
            for (Body b : bodies) {
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            currentTime += dt;
        }
        /* Universe Print */
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }
}
