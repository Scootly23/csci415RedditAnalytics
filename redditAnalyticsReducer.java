import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import java.util.*;
import org.apache.hadoop.io.LongWritable;

public class redditAnalyticsReducer extends Reducer<Text,LongWritable,Text,LongWritable>{
	
	private TreeMap<LongWritable, Text> dataToRecordMap = new TreeMap<LongWritable,Text>();

	private Text outAddress = new Text();
	
	public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException{
		int total = 0;
		System.out.println("I'm here");
		
		LongWritable k1 = new LongWritable();
		IntWritable i1 = new IntWritable();
		for( LongWritable value : values){
			total++;
		}
		k1.set(total);
		dataToRecordMap.put(k1, new Text(key));
		
	//	context.write(key,k1);

	}
	
	protected void cleanup(Context context) throws IOException, InterruptedException{
		for(Map.Entry<LongWritable,Text> entry : dataToRecordMap.entrySet()){
			LongWritable key = entry.getKey();
			Text l = entry.getValue();
			context.write(l, key);
		}
	}
}
