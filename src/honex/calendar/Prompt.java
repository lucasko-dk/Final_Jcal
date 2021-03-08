package honex.calendar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author Lucas ko # 입력된 월의 1일의 요일을 입력 받는다. # 입력받은 요일 부터 1일이 출력되어 말일까지 주별로 7일씩
 *         출력되도록 작성.
 */


public class Prompt {
	
	private final static String PROMPT_YEAR = "YEAR> ";
	private final static String PROMPT_MONTH = "MONTH> ";
//	private final static String PROMPT_WEEKDAY = "WEEKDAY(SU,MO,TU,WE,TH,FR,SA)> ";


	private void initPrompt() {
		//json방식으로 저장하기 
		//프로그램 시작할때 일정 불러오기 
		//일정 화일로 저장하기 
		//switch문으로 변경 
		//class로 구현하기 
		//해쉬맵 생성자 초기화 + try catch 사용
		//일정을 date객체로 저장하고 및 해쉬맵에서 검색 로직 추가
		
		


		Scanner scanner = new Scanner(System.in);
		Calendar calendar = new Calendar();
		boolean isLoop = true;
		
		cmdPrintMenu(calendar);

		while (isLoop) {
			System.out.println("명령 ( 1, 2, 3, h, q ) ");
			String cmd = scanner.next();
			switch (cmd) {
				case "1":
					cmdRegister(scanner, calendar);
					break;
				case "2":
					cmdSearch(scanner, calendar);
					break;
				case "3":
					cmdView(scanner, calendar);
					break;
				case "h":
					cmdHelp();
					break;
				case "q":
					isLoop = false;
					break;
			}
		}
		
		scanner.close();
		System.out.println("Thank you..Bye!!");
	}

	private void cmdPrintMenu(Calendar c) {
		System.out.println("================");
		System.out.println("저장된 일정");
		System.out.println("================"+"\n");
		System.out.println("----------------");
		
		try {
			c.savedPlan();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("----------------");
		System.out.println("1. 일정 등록");
		System.out.println("2. 일정 검색");
		System.out.println("3. 달력 보기");
		System.out.println("h. 도움말");
		System.out.println("q. 종료");
		System.out.println("----------------");
		
	}

	private void cmdSearch(Scanner s, Calendar c) {
		System.out.println("[일정 검색]");
		System.out.println("날짜를 입력해 주세요(yyyy-MM-dd)");
		String date = s.next();
		PlanItem plan ; 
		plan = c.searchPlan(date);
		if (plan != null) { 
			System.out.println(plan.detail);
		} else { 
			System.out.println("일정이 없습니다.");
		}
//		System.out.println(plan);
	}

	private void cmdRegister(Scanner s , Calendar c) {
		// TODO Auto-generated method stub
		System.out.println("[새 일정 등록]");
		System.out.println("날짜를 입력해 주세요(yyyy-MM-dd)");
		String date = s.next();
		String text = ""; 
		System.out.println("일정을 입력해 주세요 (문장끝에 ;)");
		while(true) {
			String word = s.next();
			text += word + " "; 
            if (word.endsWith(";")) break;			
		}
		text = text.replace(";", "");
		c.registerPlan(date,text);
	}
	
	public void cmdView(Scanner s, Calendar c) {
		// weekday를 입력받지 않고 자동계산하여 출력한다
		// 숫자 입력받아 그달의 최대일수 출력
//		Scanner scanner = new Scanner(System.in);
//		Calendar cal = new Calendar();

		while (true) {
			System.out.print(PROMPT_YEAR);
			int year = s.nextInt();
			if (year == -1) {
				System.out.println("종료합니다.-EXIT");
				break;
			}
			System.out.print(PROMPT_MONTH);
			int month = s.nextInt();
			if (month < 1 || month > 12) {
				System.out.println("잘못된 월을 입력했습니다");
				break;
			}
			if (month > 12) {
				continue;
			}

			int weekdayint = 0;
//			System.out.print(PROMPT_WEEKDAY);
//			String weekday = scanner.next();
//			int weekdayint = cal.intWeekDay(weekday.toUpperCase());
//			if (weekdayint==-1) {
//				System.out.println("잘못된 요일을 입력했습니다");
//				break;
//			}

			weekdayint = c.getWeekDay(year, month, 1);
//			System.out.printf("%d 월의 최대 일수는 %d 입니다. \n", mon, cal.getMaxDaysOfMonth(mon));
			c.printCalendar(year, month, weekdayint);
		}

//		System.out.println("Bye!!" );	
//        scanner.close();

	}

	public void cmdView() {
		// weekday를 입력받지 않고 자동계산하여 출력한다
		// 숫자 입력받아 그달의 최대일수 출력
		Scanner scanner = new Scanner(System.in);
		Calendar cal = new Calendar();

		while (true) {
			System.out.print(PROMPT_YEAR);
			int year = scanner.nextInt();
			if (year == -1) {
				System.out.println("종료합니다.-EXIT");
				break;
			}
			System.out.print(PROMPT_MONTH);
			int month = scanner.nextInt();
			if (month < 1 || month > 12) {
				System.out.println("잘못된 월을 입력했습니다");
				break;
			}
			if (month > 12) {
				continue;
			}

			int weekdayint = 0;
//			System.out.print(PROMPT_WEEKDAY);
//			String weekday = scanner.next();
//			int weekdayint = cal.intWeekDay(weekday.toUpperCase());
//			if (weekdayint==-1) {
//				System.out.println("잘못된 요일을 입력했습니다");
//				break;
//			}

			weekdayint = cal.getWeekDay(year, month, 1);
//			System.out.printf("%d 월의 최대 일수는 %d 입니다. \n", mon, cal.getMaxDaysOfMonth(mon));
			cal.printCalendar(year, month, weekdayint);
		}

		System.out.println("Bye!!");
		scanner.close();

	}
	
	public void cmdHelp() {
		// TODO Auto-generated method stub

	}


	public static void main(String[] args){

		Prompt prompt = new Prompt();
		prompt.initPrompt();
//		prompt.runPrompt();

	}

}