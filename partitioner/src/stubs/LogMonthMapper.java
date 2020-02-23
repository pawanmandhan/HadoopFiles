package stubs;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LogMonthMapper extends Mapper<LongWritable, Text, Text, Text> {

  /**
   * Example input line:
   * 96.7.4.14 - - [24/Apr/2011:04:20:11 -0400] "GET /cat.jpg HTTP/1.1" 200 12433
   *
   */
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    
	  /* TODO: implement */
	  
	  String line = value.toString();
	  //Regex pattern of ip input
	  Pattern patternOfIP = Pattern.compile("(\\A|\\s)(?:(?:25[0-5]|2[0-4]\\d|[01]?\\d{1,2})\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d{1,2})(\\s|\\z)");
	  Pattern patternOfMonth = Pattern.compile("(?<=\\[(([0-2]?\\d)|([3][0-2]))/)([A-Z][a-z]{2})(?=/(([1][9]{2}\\d)|([2][0][01]\\d)).*\\])");
	  Matcher ip = patternOfIP.matcher(line);
	  Matcher month = patternOfMonth.matcher(line);
	  
	  //Write key-value for ip addr and month
	  if(ip.find() && month.find()) {
		  //System.out.println("This line does agree: " + line);
		  context.write(new Text(ip.group().trim()), 
				  new Text(month.group().toLowerCase().trim()));
	  } else {
		  //System.out.println("This line does not: " + line);
		  //For lines that do not match, skip
	  }
	  
	  /* Done */
	  
  }
}
