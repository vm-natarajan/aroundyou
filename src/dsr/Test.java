package dsr;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import support.mDB;

public class Test {

	public static void main(String[] args) {
		
		mDB db = mDB.getInstance();
		String test = db.searchNewsByPeriodAndSection( "1","travel");
		System.out.println(test);
		//System.out.println(msg.size());
		
		
	}
}
