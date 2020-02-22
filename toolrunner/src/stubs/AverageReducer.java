package stubs;
import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AverageReducer extends Reducer<Text, IntWritable, Text, DoubleWritable> {

  @Override
  public void reduce(Text key, Iterable<IntWritable> values, Context context)
      throws IOException, InterruptedException {

	  //Get the average of the letter
	  double sum = 0.0; double count = 0.0;
	  for(IntWritable value : values) {
		  sum += value.get();
		  count++;
	  }
	  double averageVal = (sum / count);
	  
	  //Write the average and key (letter)
	  context.write(key, new DoubleWritable(averageVal));
  }
}