package honex.calendar;

import java.util.Scanner;

public class Sum {
    public static void main(String[] args) {
    	int a, b ; 
		Scanner scanner = new Scanner(System.in);
		String s1, s2;
		System.out.println("두수 ? : " );
		s1 = scanner.nextLine();
		s2 = scanner.nextLine();
		
		a=Integer.parseInt(s1);
		b=Integer.parseInt(s2);
		System.out.printf("%d 와 %d 의  합은 %d 입니다.",a,b,a+b );
//		String[] splitvalue = InputValue.split(",");
//		
//		int first = Integer.parseInt(splitvalue[0]);
//		int second = Integer.parseInt(splitvalue[1]);
//
//		System.out.println("두수 ? : " );
//		System.out.println(splitvalue[0] + " + " + splitvalue[1] + " = " + (first+second));
		scanner.close();
    }
}