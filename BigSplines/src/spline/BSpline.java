package spline;

import java.util.Arrays;
import java.util.List;
import point.*;

public class BSpline {
	
	public BSpline(int degree, float[] knots, Point3D[] controlPoints ) {
		if(knots.length <= 2 * degree)
			throw new IllegalArgumentException("You need atleast 2n + 1 knots, where n is the degree.");
		if(controlPoints.length != knots.length - degree - 1)
			throw new IllegalArgumentException("For a B-spline with 2n + p + 1 knots you need n + p control points.");
		
		this.degree = degree;
		setKnots(knots);
		setControlPoints(points);
		
	}
	
	public int degree() {
		return degree;
	}
	
	private final int degree;
	
	private void setKnots(float[] knots) {
		this.knots = arrayCopy(knots);
		Arrays.sort(this.knots);
	}
	
	
	private float[] knots;
	
	
	private void setControlPoints(Point3D[] controlPoints) {
		points = arrayCopy(controlPoints);
	}
	
	private Point3D[] points;
	
	public float eval(float u) {
		int l = range(knots, u);
		
		return -1;
	}
	
	
	private <T> T[] arrayCopy(T[] array) {
		int n = array.length;
		@SuppressWarnings("unchecked")
		T[] copy = (T[]) new Object[n];
		
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
	
	private int range(float[] a, float u) {
		int n = a.length;
		if(u < a[degree] || a[n-1] < u  )
			throw new IllegalArgumentException("u has to be between knots(degree()) and knots(amountOfKnots()-1)");
		int upper = n;
		int lower = 0;

		while(upper >= lower){
			int i = (upper+lower)/2;
			
			if(u < a[i])
				upper = i;
			else if(a[i+1] <= u)
				lower = i+1;
			else 
				return i;
		}
		return -1;
			
	}
	

}
