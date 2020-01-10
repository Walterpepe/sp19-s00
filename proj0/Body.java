import java.lang.Math.*;

public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public static final double G = 6.67e-11d;

    public Body(double xP, double yP, double xV, double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Body(Body b){
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance ( Body b ) {
        return  Math.sqrt(Math.pow(b.xxPos - this.xxPos, 2) + Math.pow(b.yyPos - this.yyPos, 2));
    }

    public double calcForceExertedBy( Body b ) {
       return G * this.mass * b.mass / Math.pow(calcDistance(b),2);
    }

    public double calcForceExertedByX( Body b ) {
        return  calcForceExertedBy(b) * ( b.xxPos - this.xxPos ) / calcDistance (b);
    }

    public double calcForceExertedByY( Body b ) {
        return  calcForceExertedBy(b) * ( b.yyPos  - this.yyPos ) / calcDistance (b);
    }

    public double calcNetForceExertedByX( Body[] bs) {
        double netForce = 0;
        for (Body b : bs) {
            if ( this.equals(b) ) {
                continue;
            }
            netForce += calcForceExertedByX(b);
        }
        return  netForce;
    }

    public double calcNetForceExertedByY( Body[] bs) {
        double netForce = 0;
        for (Body b : bs) {
            if ( this.equals(b) ) {
                continue;
            }
            netForce += calcForceExertedByY(b);
        }
        return  netForce;
    }

    public void update(double dt, double fX, double fY){

        this.xxVel = this.xxVel + dt * (fX / this.mass);
        this.yyVel = this.yyVel + dt * (fY / this.mass);

        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }


    public void draw() {

        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }


}
