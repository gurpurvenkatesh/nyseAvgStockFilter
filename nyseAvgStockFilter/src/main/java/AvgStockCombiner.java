import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class AvgStockCombiner extends Reducer<CustomTextPair, CustomLongPair, CustomTextPair, CustomLongPair> {

	private static Long totVolume = new Long(0);
	private static Long numOFRecords = new Long(0);
	
	private static CustomLongPair result = new CustomLongPair(); 
	
	public void reduce(CustomTextPair key, Iterable<CustomLongPair> values, Context context) throws IOException, InterruptedException{
		
		totVolume = new Long(0);
		numOFRecords = new Long(0);
		
		for(CustomLongPair value : values){
			totVolume += value.getFirst().get();
			numOFRecords += value.getSecond().get();
		}
		
		result.setFirst(new LongWritable(totVolume));
		result.setSecond(new LongWritable(numOFRecords));
		
		context.write(key, result);
	}
}