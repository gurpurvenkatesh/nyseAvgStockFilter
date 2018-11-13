import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class AvgStockDriver extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		
		Job job = Job.getInstance(getConf());
		job.setJarByClass(getClass());
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setInputFormatClass(CombineTextInputFormat.class);	// Used to reach the multiple files. 
		job.setMapperClass(AvgStockMapper.class);
		
		job.setMapOutputKeyClass(CustomTextPair.class);
		job.setMapOutputValueClass(CustomLongPair.class);
		
		job.setCombinerClass(AvgStockCombiner.class);
		job.setReducerClass(AvgStockReducer.class);
		
		job.setNumReduceTasks(4);
		
		job.setOutputKeyClass(CustomTextPair.class);
		job.setOutputValueClass(CustomLongPair.class);
		
		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		System.exit(ToolRunner.run(new AvgStockDriver(), args));
	}

}