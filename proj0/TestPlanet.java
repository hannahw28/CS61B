public class TestPlanet{ 
	public static void main(String[] args){
	Planet p1 = new Planet(1.0, 2.0, 3.0, 4.0, 5.0, "jupiter.gif");
	Planet p2 = new Planet(2.0, 4.0, 3.0, 4.0, 5.0, "earth.gif");
	double fX = p1.calcForceExertedByX(p2);
	double fY = p1.calcForceExertedByY(p2);
	System.out.println("X force is "+fX);
	System.out.println("Y force is "+fY);
	}
}