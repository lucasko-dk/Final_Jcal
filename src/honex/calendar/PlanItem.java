package honex.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlanItem {
	
	public static Date planDate;
	public String detail;
	public String strDate;
	
	public static Date getDatefromString(String date) {
//		Date date1 = null;
		try {
			planDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return planDate; 
		
	}
	
	public PlanItem(String date, String detail) {
		this.planDate = getDatefromString(date);
		this.detail = detail;
	}
	
	public String getStringfromDate(Date planDate) {
		strDate = planDate.toString();
		return strDate; 
	}

}