package hotel_b;

import java.util.Scanner;

//05-11-2023 12:37 _주하태 input 한곳에 모으기

public class InputData_B {
	final static Scanner SCAN= new Scanner(System.in);
	
	// 객실 번호 입력 메소드
	public int userInputRoomNumber() {
		while (true) {
			if (SCAN.hasNextInt()) {
				int num = SCAN.nextInt();
				if ((num >= 101 && num <= 110) || (num >= 201 && num <= 210) || (num >= 301 && num <= 310)) {
					SCAN.nextLine();
					return num;
				} else {
					System.out.println("입력한 번호에 해당하는 객실이 없습니다.");
					SCAN.nextLine();
					continue;
				}
			} else {
				System.out.println("정수 값을 입력하세요.");
				SCAN.nextLine();
				continue;
			}
		}
	}
	// 선택할 숫자 입력
	public int userInputNumber() {
		while (true) {
			if (SCAN.hasNextInt()) {
				int num = SCAN.nextInt();
				SCAN.nextLine();
				return num;
			} else {
				System.out.println("정수 값을 입력하세요.");
				SCAN.nextLine();
				continue;
			}
		}
	}
	
	// 문자 입력 메소드
	public String userInputString() {
		String text = SCAN.next();
		return text;
	}


}
