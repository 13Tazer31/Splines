package point;

public class Vector3D extends AbstractVector{

	public Vector3D(float x, float y, float z) {
		super(x, y, z);
	}
	
	@Override
	public Vector3D multiply(float f) {
		return new Vector3D(f*x(), f*y(), f*z());
	}

	@Override
	public Vector3D add(Vector3D v) {
		return new Vector3D(x()+v.x(), y()+v.y(), z()+v.z());
	}
	
	@Override
	public int hashCode() {
		return super.hashCode() + 17;
	}
	
	@Override
	public boolean equals(Object o) {
		if(! (o instanceof Vector3D))
			return false;
		Vector3D p = (Vector3D) o;
		return x() == p.x() && 
				y() == p.y() && 
				 z() == p.z();
	}
	
	
	


}
