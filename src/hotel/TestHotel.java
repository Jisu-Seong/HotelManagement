package hotel;

import java.util.Scanner;

import customer.Person;
import hotel_b.HotelManagement_B;
import hotel_b.PresentationRoom_B;

/*
호텔의 데스크 직원 또는 매니저가 사용할 수 있는 호텔 객실 관리프로그램

Java 어플리케이션이 실행가능한 개인PC에서 사용합니다.
Console 입출력을 통해 사용합니다.

사용자는 제공하는 기능 메뉴를 보고 입력을 통해 기능을 활용할 수 있습니다.
1. 현재의 각 객실의 상태 o 와 집계된 개수를 시각적으로 확인 가능합니다.
- 빈 객실
- 예약된 객실
- 투숙중인 객실

2. 각 객실의 예약/투숙 고객의 기본 정보(이름, 전화번호)를 확인 가능합니다. o

3. 객실 상태 확인/변경 기능
- 고객명/전화번호로 검색 o
- 예약/예약 취소 o
- 체크인/체크아웃 o

--------------------------------------
o
2층부터 5층까지의 객실이 존재. 각 층마다 20개의 객실이 있다.
방번호 규칙 : 3자리의 정수. 첫자리는 층수를 의미한다.
방 분류
싱글 : 방번호의 끝자리 수는 홀수이다.
더블 : 방번호의 끝자리 수는 짝수이다.

--------------------------------------

옵션

고객 등급
날짜 및 시간
금액 및 결제
청소 상태

 */
public class TestHotel {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		PresentationRoom p = new PresentationRoom();
		PresentationRoom_B pB = new PresentationRoom_B();
		
		int num = 0;
		while (true) {
			System.out.println("호텔 객실 관리 프로그램");
			System.out.println("1. A동 2. B동");
			num = scanner.nextInt();
			scanner.nextLine();
			if (num == 1) {
				p.showMenu();
				break;
			}
			if (num == 2) {
				pB.showMenu();
				break;
			}
			if (!(num == 1 || num == 2))
				continue;
		}
	}
}
