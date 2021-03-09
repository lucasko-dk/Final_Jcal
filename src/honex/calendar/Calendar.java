package honex.calendar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Calendar {

	private static final int[] MAX_DAYS = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static final int[] LEAP_MAX_DAYS = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static final String SAVE_JSON = "Plan.json";
	private static final String SAVE_TEMP = "Temp.dat";

	private HashMap<Date, PlanItem> planMap;

	public Calendar() {
		planMap = new HashMap<Date, PlanItem>();
	}

	public void savedPlan() throws IOException {
	
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        
        File f = new File(SAVE_JSON); 
        if(f.exists()) {  
        
	        try (FileReader reader = new FileReader(SAVE_JSON))
	        {
	            //Read JSON file
	            Object obj = jsonParser.parse(reader);
	 
	            JSONArray planningList = (JSONArray) obj;
	            //System.out.println(planningList);
	             
	            //Iterate over employee array
	            planningList.forEach( emp -> parsePlanningObject( (JSONObject) emp ) );
	            System.out.println("----------------"+"\n");
	 
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        
        }
            
	}
	
	  private static void parsePlanningObject(JSONObject planning) 
	    {
	        //Get employee object within list
	        JSONObject planningObject = (JSONObject) planning.get("planning");
	         
	        //Get employee first name
	        String date = (String) planningObject.get("date");    
	        System.out.println(date);
	         
	        //Get employee last name
	        String plan = (String) planningObject.get("plan");  
	        System.out.println(plan);
	         
	    }

	public PlanItem searchPlan(String strDate) {
		Date date = PlanItem.getDatefromString(strDate);
		return planMap.get(date);
	}

	public void registerPlan(String strDate, String Plan) {
		PlanItem p = new PlanItem(strDate, Plan);
		planMap.put(p.planDate, p);

		JSONObject jobj = new JSONObject();

		jobj.put("date", strDate);
		jobj.put("plan", Plan);
		
		JSONObject jobt= new JSONObject(); 
		jobt.put("planning", jobj);
		
		//Add planning to list
        JSONArray jobList = new JSONArray();
        jobList.add(jobt);
        
		File f = new File(SAVE_TEMP);
		FileWriter fw;
		
		try {
			fw = new FileWriter(f, true);
			fw.write("  \r\n" + jobList.toJSONString() + "\r\n" +  "   \r\n");
			fw.flush();
			fw.close();
			replaceInFile(f);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public void replaceInFile(File file) throws IOException {

		File f= new File(SAVE_JSON);           //file to be delete  
		f.delete();
		
	    File tempFile = new File(SAVE_JSON);
	    FileWriter fw;
	    fw = new FileWriter(tempFile, true);

	    Reader fr = new FileReader(file);
	    BufferedReader br = new BufferedReader(fr);

        fw.write("[  \n");
	    while(br.ready()) {
	        String tmpstr1=br.readLine().replace("[", " ");
	        String tmpstr2=tmpstr1.replace("]", " ");
	        fw.write(tmpstr2 + "\n");
	    }
        fw.write("]  \n");
        
	    fw.close();
	    br.close();
	    fr.close();

	}
	

//	public static void main(String[] args) {
//		Calendar calendar = new Calendar();
//		int result = calendar.getWeekDay(1970, 2,1);
//	}

	public static int getWeekDay(int year, int month, int day) {
		int sYear = 1970;
//		int sMonth = 1;
		int sDay = 0;
		final int STANDARD_WEEKDAY = 3; // 1970/1/1 = thursday

		// year day
		if (year - sYear > 0) {

			for (int i = 0; i < year - sYear; i++) {

				if (isLeapYear(year - i - 1)) {
					sDay = sDay + 366;
				} else {
					sDay = sDay + 365;
				}
			}

		}

		// month day

		for (int i = 0; i < month - 1; i++) {
			if (isLeapYear(year)) {
				sDay = sDay + LEAP_MAX_DAYS[i];
			} else {
				sDay = sDay + MAX_DAYS[i];
			}
		}

		// day
		sDay = sDay + day;

		int weekday;
		// week day
		if (year == 1970 && month == 1) {
			weekday = STANDARD_WEEKDAY;
		} else {
			weekday = (sDay) % 7;
			weekday = weekday + (STANDARD_WEEKDAY - 1);
		}

		if (weekday == 7) {
			weekday = 0;
		}
		return weekday;
	}

	public static int intWeekDay(String weekday) {
		switch (weekday) {
		case "SU":
			return (0);
		case "MO":
			return (1);
		case "TU":
			return (2);
		case "WE":
			return (3);
		case "TH":
			return (4);
		case "FR":
			return (5);
		case "SA":
			return (6);
		default:
			return (-1);
		}
	}

	private static Boolean isLeapYear(int year) {
		if (year % 4 == 0 && year % 100 == 0 && year % 400 == 0) {
			return true;
		} else if (year % 4 == 0 && year % 100 == 0) {
			return false;
		} else if (year % 4 == 0) {
			return true;
		} else {
			return false;
		}

	}

	public static int getMaxDaysOfMonth(int year, int mon) {

		if (isLeapYear(year)) {
			return LEAP_MAX_DAYS[mon - 1];
		} else {
			return MAX_DAYS[mon - 1];
		}
	}

	public void printCalendar(int year, int month, int weekdayint) {

		int cnt = 0;
		System.out.printf("<< %4d년%3d월 >> \n", year, month);
		System.out.println(" MO TU WE TH FR SA SU");
		System.out.println("---------------------");

		for (int j = 0; j < weekdayint; j++) {
			System.out.print("   ");
		}

		cnt = weekdayint + 1;

		for (int i = 0; i < getMaxDaysOfMonth(year, month); i++) {
			System.out.printf("%3d", i + 1);
			if ((cnt) % 7 == 0) {
				System.out.println();
				cnt = 0;
			}
			cnt++;
		}

		System.out.println("\n");

	}

}