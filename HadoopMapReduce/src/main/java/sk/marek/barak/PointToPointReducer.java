package sk.marek.barak;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class PointToPointReducer extends MapReduceBase implements Reducer<Euclidean3DSpacePoint, DoubleWritable, Euclidean3DSpacePoint, DoubleWritable>{

	@Override
	public void reduce(Euclidean3DSpacePoint key,
			Iterator<DoubleWritable> values,
			OutputCollector<Euclidean3DSpacePoint, DoubleWritable> outPutCollector,
			Reporter reporter) throws IOException {
		
		double distance = 0.0;
		while(values.hasNext()){
			
			distance += values.next().get();
			System.out.println(distance);
			
		}
		outPutCollector.collect(key, new DoubleWritable(distance));
		
		
		
	}

}
