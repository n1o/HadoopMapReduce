package sk.marek.barak;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;


public class Euclidean3DSpacePoint implements WritableComparable<Euclidean3DSpacePoint>{
	private double x;
	private double y;
	private double z;

	public Euclidean3DSpacePoint(){
		this.x = 0.0;
		this.y = 0.0;
		this.z = 0.0;
		
	}
	public Euclidean3DSpacePoint(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	@Override
	public void readFields(DataInput arg0) throws IOException {
		this.x = arg0.readDouble();
		this.y = arg0.readDouble();
		this.z = arg0.readDouble();
		
	}
	@Override
	public String toString() {
		return "[" + 
				Double.toString(x) + ", " +
				Double.toString(y) + ", " +
				Double.toString(z) + "]";
	};
	@Override
	public void write(DataOutput arg0) throws IOException {
		arg0.writeDouble(x);
		arg0.writeDouble(y);
		arg0.writeDouble(z);
		
	}

	@Override
	public int compareTo(Euclidean3DSpacePoint arg0) {
		double myDistance = this.getDistanceFromOrigin();
		double otherDistance = arg0.getDistanceFromOrigin();
		return Double.compare(myDistance, otherDistance);
	}
	
	
	public double getDistanceFromOrigin(){
		return Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z));
		
	}
	public double distatanceFromAnotherPoint(Euclidean3DSpacePoint point){
		return Math.sqrt(
				Math.pow(this.x-point.getX(), 2)+
				Math.pow(this.y - point.getY(), 2) +
				Math.pow(this.z - point.getZ(), 2)
				);
		
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Euclidean3DSpacePoint)){
			return false;
		}
		Euclidean3DSpacePoint point = (Euclidean3DSpacePoint) obj;
		return this.x == point.getX() && this.y == point.getY() && this.z == point.getZ();
		
	};

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
	

}
