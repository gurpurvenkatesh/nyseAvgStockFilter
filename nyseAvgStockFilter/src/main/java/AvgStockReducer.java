import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class AvgStockReducer extends Reducer<CustomTextPair, CustomLongPair, CustomTextPair, CustomLongPair> {

	private static Long totVolume = new Long(0);
	private static Long numOFRecords = new Long(0);
	
	private static Long avgVolume = new Long(0);
	private static CustomLongPair result = new CustomLongPair(); 
	
	public void reduce(CustomTextPair key, Iterable<CustomLongPair> values, Context context) throws IOException, InterruptedException{
		
		totVolume = new Long(0);
		numOFRecords = new Long(0);
		//avgVolume = (long) 0;
		
		for(CustomLongPair value : values){
			totVolume += value.getFirst().get();
			numOFRecords += value.getSecond().get();
		}
		
		if(numOFRecords!=0)
			avgVolume = totVolume/numOFRecords;
		else 
			avgVolume = (long) 0;
		
		result.setFirst(new LongWritable(avgVolume));
		result.setSecond(new LongWritable(numOFRecords));
		
		context.write(key, result);
	}
}