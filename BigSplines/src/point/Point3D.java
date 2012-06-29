package point;

public class Point3D extends AbstractVector{
	
	public Point3D(float x, float y, float z) {
		super(x, y, z);
	}

	@Override
	public Point3D multiply(float f) {
		return new Point3D(f*x(), f*y(), f*z());
	}
	
	public Vector3D subtract(Point3D p) {
		return new Vector3D(x()-p.x(), y()-p.y(), z()-p.z());
	}
	
	@Override
	public Point3D add(Vector3D v) {
		return new Point3D(x()+v.x(), y()+v.y(), z()+v.z());
	}
	
	public static final Point3D ORIGIN = new Point3D(0, 0, 0);
	
	@Override
	public int hashCode() {
		return super.hashCode() + 37;
	}
	
	@Override
	public boolean equals(Object o) {
		if(! (o instanceof Point3D))
			return false;
		Point3D p = (Point3D) o;
		return x() == p.x() && 
				y() == p.y() && 
				 z() == p.z();
	}

}
