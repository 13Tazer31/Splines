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
	
	public abstract AbstractVector multiply(float f);
	
	public AbstractVector negate() {
		return multiply(-1);
	}
	
	public abstract AbstractVector add(Vector3D v) ;
	
	public double length() {
		return Math.sqrt(x()*x()+y()*y()+z()*z());
	}
	
	public double norm(int p) {
		double result = Math.pow(x(), p) + Math.pow(y(), p) + Math.pow(z(), p);
		return Math.pow(result, 1/p);
	}
	
	@Override
	public int hashCode() {
		int hash = new Float(x()).hashCode();
		hash = (hash >> 7) ^ new Float(y()).hashCode();
		hash = (hash >> 7) ^ new Float(z()).hashCode();
		
		return hash;
	}
	
	public String toString() {
		return "("+x()+", " + y() + ", " + z() + ")";
	}
	

}
