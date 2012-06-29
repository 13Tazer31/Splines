package point;

public abstract class AbstractVector {
	
	public AbstractVector(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	private final float x;
	private final float y;
	private final float z;
	
	public float x() {
		return x;
	}
	
	public float y() {
		return y;
	}
	
	public float z() {
		return z;
	}
	
	public abstract AbstractVector add(Vector3D v) ;
	
	public abstract AbstractVector negate() ;
	
	public double length() {
		return Math.sqrt(x()*x()+y()*y()+z()*z());
	}
	
	public double norm(int p) {
		double result = Math.pow(x(), p) + Math.pow(y(), p) + Math.pow(z(), p);
		return Math.pow(result, 1/p);
	}
	

}
