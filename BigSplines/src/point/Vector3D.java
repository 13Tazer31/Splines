package point;

public class Vector3D extends AbstractVector{

	public Vector3D(float x, float y, float z) {
		super(x, y, z);
	}

	@Override
	public Vector3D add(Vector3D v) {
		return new Vector3D(x()+v.x(), y()+v.y(), z()+v.z());
	}
	
	public Vector3D negate() {
		return new Vector3D(-x(), -y(), -z());
	}
	
	
	


}
