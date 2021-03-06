public class NBody
{
	public static double readRadius(String path)
	{
		In in = new In(path);
		int num = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String path)
	{
		In in = new In(path);
		int num = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[num];
		for (int i=0; i<num; i++)
		{			
			double xxPos = in.readDouble();
	  		double yyPos = in.readDouble();
	  		double xxVel = in.readDouble();
	  		double yyVel = in.readDouble();
	  		double mass = in.readDouble();
	  		String imgFileName = in.readString();
	  		Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
	  		planets[i] = p;
		}
		return planets;
	}

	public static void main(String[] args)
	{
		
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);

		String filepath = args[2];
		StdDraw.setScale(-1e14,1e14);
		String back = "images/starfield.jpg";
		StdDraw.picture(1e13,1e13,back);
		Planet[] planets = readPlanets(filepath);
		double radius = readRadius(filepath);
		int N = (int)(T/dt);
		int M = planets.length;
		int x = 1;
		double[][] xForces = new double[M][N];
		double[][] yForces = new double[M][N];

		for (int i=0; i<M; i++)
		{	
			for (int j=0; j<=N; j++)
				{
					xForces[i][j] = planets[i].calcNetForceExertedByX(planets);
					yForces[i][j] = planets[i].calcNetForceExertedByY(planets);
				}
		}
		StdDraw.enableDoubleBuffering();
		for (int i=0; i<=N; i++)
			{	
			StdDraw.clear();
			StdDraw.picture(0,0,back);
			for (int j=0; j<M; j++)		
			{
				
				planets[j].update(dt, xForces[j][i], yForces[j][i]);
				planets[j].draw();

			}	
			StdDraw.show();
			StdDraw.pause(2);
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