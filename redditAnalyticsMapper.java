
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
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
import java.util.Calendar;

    public class redditAnalyticsMapper extends Mapper<Object, Text, Text,LongWritable>{
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException{
		Text title = new Text();
		LongWritable l = new LongWritable();
			Long data = 0L;
			String line = value.toString().toLowerCase();
			String[] fields = line.trim().split("``");
			String address = fields[0];
			String[] words = address.split(" ");
			for(int x = 0;x<words.length;x++){
				words[x].trim();
				words[x] = words[x].replaceAll("\"|\\(|\\)|'s|'|!|\\?|\\.|:|,","");
				//words[x].replace("'s","");
				//words[x].replaceAll("'","");
				//words[x].replace("!","");
				//words[x].replace("?","");
				//words[x].replace(":","");
			
							
			}
			String timestamp = fields[1];
			long t = Long.parseLong(timestamp);
			t = 86400*((int)t/86400);
			for(int i = 0; i < words.length; i++){
				title.set(words[i]);
				l.set(t);
				context.write(title, l);
			}	
	}

}
