package sk.marek.barak;

import java.io.IOException;

import org.apache.commons.lang.Validate;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.LineRecordReader;
import org.apache.hadoop.mapred.RecordReader;

public class PointToPointRecordReader implements RecordReader<Euclidean3DSpacePoint, Euclidean3DSpacePoint> {

	private LineRecordReader lineReader;
	private LongWritable lineKey;
	private Text lineValue;
	
	public PointToPointRecordReader(JobConf job, FileSplit split) throws IOException {
		lineReader = new LineRecordReader(job, split);
		lineKey = lineReader.createKey();
		lineValue = lineReader.createValue();
	}

	@Override
	public void close() throws IOException {
		lineReader.close();
		
	}

	@Override
	public Euclidean3DSpacePoint createKey() {
		// TODO Auto-generated method stub
		return new Euclidean3DSpacePoint();
	}

	@Override
	public Euclidean3DSpacePoint createValue() {
		// TODO Auto-generated method stub
		return new Euclidean3DSpacePoint();
	}

	@Override
	public long getPos() throws IOException {
		// TODO Auto-generated method stub
		return lineReader.getPos();
	}

	@Override
	public float getProgress() throws IOException {
		// TODO Auto-generated method stub
		return lineReader.getProgress();
	}

	@Override
	public boolean next(Euclidean3DSpacePoint key, Euclidean3DSpacePoint value)
			throws IOException {
		if(!lineReader.next(lineKey, lineValue)){
			return false;
		}
		
		String[] pieces = lineValue.toString().split(",");
		if(pieces.length != 6){
			throw new IOException("Invalid record recieved");
		}
		double kx, ky,kz, vx, vy, vz;
		try{
			kx = Double.parseDouble(pieces[0]);
			ky = Double.parseDouble(pieces[1]);
			kz = Double.parseDouble(pieces[2]);
			vx = Double.parseDouble(pieces[3]);
			vy = Double.parseDouble(pieces[4]);
			vz = Double.parseDouble(pieces[5]);
			
			
		}catch(NumberFormatException nfx){
			throw new IOException("Error parsing points ", nfx);
		}
		key.setX(kx);
		key.setY(ky);
		key.setZ(kz);
		
		value.setX(vx);
		value.setY(vy);
		value.setZ(vz);
		
		return true;
	}

}
