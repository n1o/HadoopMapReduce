package sk.marek.barak.test;

import static org.junit.Assert.*;

import java.nio.channels.Pipe;

import org.junit.Test;

import sk.marek.barak.Euclidean3DSpacePoint;

public class Euclidean3DSpacePointTest {

	
	@Test
	public void testConstruction(){
	Euclidean3DSpacePoint point = new Euclidean3DSpacePoint();
	assertEquals(0.0, point.getX(), 0.0);
	assertEquals(0.0, point.getY(), 0.0);
	assertEquals(0.0, point.getZ(), 0.0);
	Euclidean3DSpacePoint point2 = new Euclidean3DSpacePoint(1.5, 3.7, 9.8);
	assertEquals(1.5, point2.getX(), 0.0);
	assertEquals(3.7, point2.getY(), 0.0);
	assertEquals(9.8, point2.getZ(), 0.0);
		
	}

	@Test
	public void testToString() {
		Euclidean3DSpacePoint point = new Euclidean3DSpacePoint();
		assertEquals("[0.0, 0.0, 0.0]", point.toString());
	}
	
	@Test
	public void testDistanceFromOrigin(){
		Euclidean3DSpacePoint point = new Euclidean3DSpacePoint(2.0, 2.0, 2.0);
		
		assertEquals(Math.sqrt(12.0), point.getDistanceFromOrigin(), 0.0001);
	}
	@Test
	public void testDistanceFromPoint(){
		Euclidean3DSpacePoint point = new Euclidean3DSpacePoint(2.0, 2.0, 2.0);
		Euclidean3DSpacePoint point2 = new Euclidean3DSpacePoint(3.0, 3.0, 3.0);
		assertEquals(Math.sqrt(3.0), point.distatanceFromAnotherPoint(point2), 0.0001);
		assertEquals(point.distatanceFromAnotherPoint(point2), point2.distatanceFromAnotherPoint(point), 0.0001);
		
	}

}
