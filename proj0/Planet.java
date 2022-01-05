import java.lang.Math;
public class Planet {
	private static final double G = 6.67e-11;
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p){
	this.xxPos = p.xxPos;
	this.yyPos = p.yyPos;
	this.xxVel = p.xxVel;
	this.yyVel = p.yyVel;
	this.mass = p.mass;
	this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		return Math.sqrt((this.xxPos-p.xxPos)*(this.xxPos-p.xxPos)+(this.yyPos-p.yyPos)*(this.yyPos-p.yyPos));
	}

	public double calcForceExertedBy(Planet p){
		double r = this.calcDistance(p);
		return G * this.mass * p.mass/(r*r);
	}

	public double calcForceExertedByX(Planet p){
		double r = this.calcDistance(p);
		double dx = p.xxPos - this.xxPos;
		double F = calcForceExertedBy(p);
		return F*dx/r;
	}

	public double calcForceExertedByY(Planet p){
		double dy=p.yyPos - this.yyPos;
		return calcForceExertedBy(p)*dy/this.calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] allPlanets){
		double netforcex=0;
		for (Planet p: allPlanets){
			if (this==p){
				continue;
			}
			netforcex += calcForceExertedByX(p);
		}
		return netforcex;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets){
		double netforcey=0;
		for (Planet p: allPlanets){
			if (this==p){
				continue;
			}
			netforcey += calcForceExertedByY(p);
		}
		return netforcey;
	}

	public void update(double dt, double fX, double fY){
		double ax= fX/this.mass;
		double ay= fY/this.mass;
		this.xxVel += dt*ax;
		this.yyVel += dt*ay;
		this.xxPos += dt*this.xxVel;
		this.yyPos += dt*this.yyVel;
	}

	public void draw(){
		StdDraw.picture(this.xxPos,this.yyPos,this.imgFileName);
	}
}
