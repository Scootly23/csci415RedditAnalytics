import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class redditAnalytics{

	public static void main(String[] args) throws Exception{
		if(args.length != 2) {
			System.err.println("Usage: redditAnalytics <input path> <output path>");
			System.exit(-1);
		}
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

