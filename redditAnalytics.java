import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.util.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.filecache.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class redditAnalytics{

	public static void main(String[] args) throws Exception{
		if(args.length < 0) {
			System.err.println("Usage: redditAnalytics <input path> <output path>");
			System.exit(-1);
		}
		
		final Configuration config = new Configuration();		
		Job job = new Job();

		job.setJarByClass(redditAnalytics.class);

		job.setJobName("Reddit Analytics");

		FileInputFormat.addInputPath(job, new Path(args[0]));

		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setMapperClass(redditAnalyticsMapper.class);
		job.setReducerClass(redditAnalyticsReducer.class);

		job.setOutputKeyClass(Text.class);

		job.setOutputValueClass(LongWritable.class);

		System.exit(job.waitForCompletion(true) ? 0 : 1);



	}



}

