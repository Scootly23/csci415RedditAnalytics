package my.books;
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
import org.json.*;

    public static class Map extends Mapper<LongWritable, Text, Text, Text>{

        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
            String title;
            Long timestamp;
            String line = value.toString();
            String[] tuple = line.split("\\n");
            try{
                for(int i=0;i<tuple.length; i++){
                    JSONObject obj = new JSONObject(tuple[i]);
                    title = obj.getString("title");
                    timestamp = obj.getLong("created_utc");
                    context.write(new Text(title), new longWritable(timestamp));
                }
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
    }
