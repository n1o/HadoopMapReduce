package sk.marek.barak;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextOutputFormat;



public class PointDistanceCounter {

	public static void main(String[] args) {
		JobConf conf = new JobConf(PointDistanceCounter.class);
	     conf.setJobName("pointdistancecounter");
		
		  conf.setOutputKeyClass(Euclidean3DSpacePoint.class);
	      conf.setOutputValueClass(DoubleWritable.class);
		
		      conf.setMapperClass(PointToPointMapper.class);
		      conf.setCombinerClass(PointToPointReducer.class);
		      conf.setReducerClass(PointToPointReducer.class);
		
	      conf.setInputFormat(PointPositionInputFormat.class);
	      conf.setOutputFormat(PointDistanceWriter.class);

	     FileInputFormat.setInputPaths(conf, new Path(args[0]));
	     FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		
		 try {
			JobClient.runJob(conf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
