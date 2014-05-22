package sk.marek.barak;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordWriter;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.util.Progressable;

public class PointDistanceWriter<Euclidean3DSpacePoint, DoubleWritable> extends FileOutputFormat<Euclidean3DSpacePoint, DoubleWritable> {

	protected static class PointWriter<Euclidean3DSpacePoint, DoubleWritable> implements RecordWriter<Euclidean3DSpacePoint, DoubleWritable> {
	    private static final String utf8 = "UTF-8";

	    private DataOutputStream out;

	    public PointWriter(DataOutputStream out) throws IOException {
	      this.out = out;
	      
	    }

	    private void writeObject(Object o) throws IOException {
	      if (o instanceof Text) {
	        Text to = (Text) o;
	        out.write(to.getBytes(), 0, to.getLength());
	      } else {
	        out.write(o.toString().getBytes(utf8));
	      }
	    }

	    public synchronized void write(Euclidean3DSpacePoint key, DoubleWritable value) throws IOException {

	      boolean nullKey = key == null || key instanceof NullWritable;
	      boolean nullValue = value == null || value instanceof NullWritable;

	      if (nullKey && nullValue) {
	        return;
	      }

	      Object keyObj = key;

	      if (nullKey) {
	        keyObj = "value";
	      }
	      out.writeBytes("Point: ");
	      writeObject(keyObj);

	      if (!nullValue) {
	    	out.writeBytes(" its sum of distances to other points: ");
	        writeObject(value);
	      }
	      out.writeBytes("\n");
	      
	    }

	    public synchronized void close(Reporter reporter) throws IOException {
	      try {
	        
	      } finally {
	        out.close();
	      }
	    }
	  }

	  public RecordWriter<Euclidean3DSpacePoint, DoubleWritable> getRecordWriter(FileSystem ignored, JobConf job,
	      String name, Progressable progress) throws IOException {
	    Path file = FileOutputFormat.getTaskOutputPath(job, name);
	    FileSystem fs = file.getFileSystem(job);
	    FSDataOutputStream fileOut = fs.create(file, progress);
	    return new PointWriter<Euclidean3DSpacePoint, DoubleWritable>(fileOut);
	  }

}
