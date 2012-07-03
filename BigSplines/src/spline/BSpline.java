package spline;

import java.util.Arrays;
import java.util.List;
import point.*;

public class BSpline {
	
	public static void main(String[] args) {
		float[] knots = {0,0,0,0,1,2,3,4,4,4,4};
		Point3D[] points = {new Point3D(0,0,0), new Point3D(0,0,1), new Point3D(0,1,0), new Point3D(1,0,0), new Point3D(1,1,1), new Point3D(2,2,2), new Point3D(3,3,3)};
		BSpline spline = new BSpline(3, knots, points);
		System.out.println(spline.recursiveEval(3));
	}
	
	/**
	 * Initialise a new B-spline of degree n, with given knots and de Boor points.
	 * 
	 * @param degree			The degree of the B-spline
	 * @param knots  			The knots used in calculating the splinefunctions,
	 * 							knots are labeled from -n to p+n.
	 * @param controlPoints 	Control points are labelled from -n to p-1, 
	 * 							where p is the amount of control points over n.
	 */
	public BSpline(int degree, float[] knots, Point3D[] controlPoints ) {
		if(knots.length <= 2 * degree)
			throw new IllegalArgumentException("You need atleast 2n + 1 knots, where n is the degree.");
		if(controlPoints.length != knots.length - degree - 1)
			throw new IllegalArgumentException("For a B-spline with 2n + p + 1 knots you need n + p control points.");
		
		this.degree = degree;
		setKnots(knots);
		setControlPoints(controlPoints);	
	}
	
	public int degree() {
		return degree;
	}
	
	private final int degree;
	
	public float knots(int i) {
		if(! isValidKnotIndex(i))
			throw new IllegalArgumentException("Index has to be between " + -degree() + " and "+ (degree()+p()) + ".");
		return knots[i+degree()];
	}
	
	public boolean isValidKnotIndex(int i) {
		return i >= -degree() && 
				i <= degree() + p();
	}
	
	private void setKnots(float[] knots) {
		this.knots = arrayCopy(knots);
		Arrays.sort(this.knots);
	}
	
	private float[] knots;
	
	public Point3D controlPoints(int i) {
		if(! isValidControlPointsIndex(i)) 
			throw new IllegalArgumentException("Index has to be between " + -degree() + " and "+ p() + ".");
		return points[i+degree()];
	}
	
	public boolean isValidControlPointsIndex(int i) {
		return i >= -degree() && 
				i < p();
	}
	
	private void setControlPoints(Point3D[] controlPoints) {
		points = arrayCopy(controlPoints);
	}
	
	public int p() {
		return points.length-degree();
	}
	
	private Point3D[] points;
	
	
	public Point3D recursiveEval(float u) {
		int range = range(u);
		System.out.println("done");
		return deBoor(degree(), range(u), u);
	}
	
	public Point3D deBoor(int k, int i, float u) {
		System.out.println(k + ": " + i);
		if(k == 0)
			return controlPoints(i); 
		
		float x = (u - knots(i)) / (knots(i+degree()+1) - knots(i));
		float[] a = {(1-x), x};
		Point3D[] points = {deBoor(k-1, i-1, u), deBoor(k-1, i, u)};
		return Point3D.affineCombination(a, points);
	}
	
	private int range(float u) {
		if(u < knots(-degree()) || knots(p()) <= u)
			throw new IllegalArgumentException("u has to be atleast " + knots(-degree()) + " and less than " + knots(p()));
		int lower = -degree();
		int upper = degree()+p();
		
		while(upper >= lower) {
			int i = (lower+upper)/2;
			if(u < knots(i))
				upper = i;
			else if(u >= knots(i+1))
				lower = i+1;
			else return i;
		}
		return -1;
	}
	
	
	private Point3D[] arrayCopy(Point3D[] array) {
		int n = array.length;
		@SuppressWarnings("unchecked")
		Point3D[] copy = new Point3D[n];
		
		for(int i = 0; i < n; i++)
			copy[i] = array[i];
		return copy;
	}
	
	private float[] arrayCopy(float[] array) {
		int n = array.length;
		float[] copy = new float[n];
		
		for(int i = 0; i < n; i++)
			copy[i] = array[i];
		return copy;
	}
	

	

}
