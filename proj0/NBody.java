public class NBody{
	public static double readRadius(String path){
		In in = new In(path); 
		int N = in.readInt();
		double radius= in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String path){
		In in = new In(path);
		int N = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[N];
		for (int i=0;i<N;i++){
			planets[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
		}
		return planets;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = NBody.readRadius(filename);
		Planet[] planets = NBody.readPlanets(filename);
		In in = new In(filename);
		int N = in.readInt();
		StdDraw.setScale(-5*radius,5*radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for (Planet p: planets){
			p.draw();
		}

		StdDraw.enableDoubleBuffering();
		for (double time=0;time<=T;time+=dt){
			Double[] xForces = new Double[N];
			Double[] yForces = new Double[N];
			for (int i=0;i<N;i++) {
				xForces[i]=planets[i].calcNetForceExertedByX(planets);
				yForces[i]=planets[i].calcNetForceExertedByY(planets);
			}
			for (int i=0; i<N;i++){
				planets[i].update(time,xForces[i],yForces[i]);
			}
			StdDraw.picture(0,0,"images/starfield.jpg");
			for (Planet p:planets){
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
		}	
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}
	}
}