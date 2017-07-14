package tvn.com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Test {
	
	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String s1 = sdf.format(new Date());
		String s2 = "2017-07-14";
		
		Date d1 = sdf.parse(s1);
		Date d2 = sdf.parse(s2);
		
		Date d3 = new Date(d1.getTime() + TimeUnit.DAYS.toMillis(1));
		
		System.out.println("d1 :" + d1);
		System.out.println("d2 :" + d2);
		System.out.println("d3 :" + d3);
		
		if(d2.equals(d3)) {
			System.out.println("OK");
		}
		
	}
}
