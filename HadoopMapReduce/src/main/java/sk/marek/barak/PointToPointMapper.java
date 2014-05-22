package sk.marek.barak;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class PointToPointMapper extends MapReduceBase implements Mapper<Euclidean3DSpacePoint, Euclidean3DSpacePoint,
Euclidean3DSpacePoint, DoubleWritable>{

	@Override
	public void map(Euclidean3DSpacePoint key, Euclidean3DSpacePoint value,
			OutputCollector<Euclidean3DSpacePoint, DoubleWritable> outPutCollector,
			Reporter reporter) throws IOException {
		
		outPutCollector.collect(key, new DoubleWritable(key.distatanceFromAnotherPoint(value)));
		
		
	}

}
