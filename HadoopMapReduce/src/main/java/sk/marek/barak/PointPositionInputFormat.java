package sk.marek.barak;

import java.io.IOException;

import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;

public class PointPositionInputFormat extends FileInputFormat<Euclidean3DSpacePoint, Euclidean3DSpacePoint>{

	@Override
	public RecordReader<Euclidean3DSpacePoint, Euclidean3DSpacePoint> getRecordReader(
			InputSplit input, JobConf job, Reporter arg2) throws IOException {
		// TODO Auto-generated method stub
		
		arg2.setStatus(input.toString());
		return new PointToPointRecordReader(job, (FileSplit)input);
	}
	

}
