package hotel_b;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import customer.CustomerManagement;
import customer.Person;
import customer.VIP_Customer;

public class PresentationRoom_B {

	static public HotelManagement_B h = new HotelManagement_B();
	static public CustomerManagement customerManagement = new CustomerManagement();
	static public final Scanner SCAN = new Scanner(System.in);
	static public CheckIn_B checkIn = new CheckIn_B();
	static public Reservation_B reservation = new Reservation_B();
	static public InputData_B inputData = new InputData_B();

	// 메인 메뉴
	// 05-10-2023 주하태 vip등록 기능 추가
	// 05-11-2023 20:20 성지수 메뉴출력 줄바꿈, 청소하기 항목 추가
	// 05-13-2023 22:25 성지수 메뉴순서 변경, 메뉴모양 변경
	public void showMenu() {
		h.resetRooms();
		h.ranCleanCondition();
		boolean condition = true;
		while (condition) {
			System.out.println("B동\n1. 객실 현황\t2. 예약 관리\t3. 체크인/체크아웃");
			System.out.println("4. 고객 검색\t5. VIP등록\t6. 청소하기");
			System.out.println("7. 프로그램 종료");
			int menuSelect = inputData.userInputNumber();

			switch (menuSelect) {
			case 1:
				menu1();
				h.divisionLine();
				break;
			case 2:
				reservation.reservation();
				h.divisionLine();
				break;
			case 3:
				checkIn.checkin();
				h.divisionLine();
				break;
			case 4:
				System.out.println("고객의 이름 혹은 전화번호로 검색");
				String userInput = inputData.userInputString();
				findCustomer(userInput);
				h.divisionLine();
				break;
			case 5:
				System.out.println("VIP 등록");
				customerManagement.userInputVIP();
				h.divisionLine();
				break;
			case 6:
				System.out.println("청소할 객실 번호 입력");
				cleaningRoom();
				h.divisionLine();
				break;
			case 7:
				System.out.println("프로그램 종료");
				h.divisionLine();
				condition = false;
			}
		}
	}

	// 방 번호 리턴
	// 05-10-2023 주하태 명칭수정 enterRoomNum으로 변경
	public int enterRoomNum() {
		System.out.println("객실 번호 입력");
		return inputData.userInputRoomNumber();
	}

	// 05-10-2023 장명근 방현황 확인
	public void roomConditionCheck() {
		boolean condition = true;
		while (condition) {
			System.out.println("1. 예약된 객실 확인\n2. 투숙중인 객실 확인\n3. 빈 객실 확인\n4. 뒤로가기");
			int choice = SCAN.nextInt();
			SCAN.nextLine();

			switch (choice) {
			case 1:
				System.out.println("-예약된 객실 번호-\t-고객 정보- ");
				h.showReservationRoom();
				break;

			case 2:
				System.out.println("-투숙중인 객실 번호-\t-고객 정보-");
				h.showCheckInRoom();
				break;

			case 3:
				System.out.println("-빈 객실 번호-");
				h.showEmptyRoom();
				break;

			case 4:
				condition = false;
				break;
			}
		}

	}

	// 인덱스값으로 방 정보 출력
	// 05-10-2023 주하태 - 방번호 얻기 식 만듬
	public boolean nameOrPhoneCollect(String nameOrPhoneNum, int i, int j) {
		return nameOrPhoneNum.equals(h.whoInTheRoom(i, j).getName())
				|| nameOrPhoneNum.equals(h.whoInTheRoom(i, j).getPhoneNumber());
	}

	// 05-11-2023 21:00 _주하태 논리 합침
	public void name(String nameOrPhoneNumber, int i, int j) {
		if (nameOrPhoneCollect(nameOrPhoneNumber, i, j)) {
			outputCustomer(i, j);
			h.printCheckDay(i, j);
		}
	}

	public void outputCustomer(int i, int j) {
		System.out.println(h.getRoomNumber(i, j) + "호실 " + h.roomType(h.getRoomNumber(i, j)));
		System.out.println(h.whoInTheRoom(i, j));
	}

	// 05-10-2023 주하태 이름 또는 전화번호로 방 정보 검색
	public void findCustomer(String nameOrPhoneNumber) {
		nameOrPhoneNumber = customerManagement.removeHyphen(nameOrPhoneNumber);
		int count = 0;
		for (int i = 0; i < h.roomsLength(); i++) {
			for (int j = 0; j < h.roomsLength(i); j++) {
				if (h.whoInTheRoom(i, j) != null)
					name(nameOrPhoneNumber, i, j);
				count++;
			}
		}
		if (count == 0)
			System.out.println("입력된 정보에 해당하는 고객이 없습니다.");
	}

	// 05-11-2023 16:54_성지수 검색 메소드 이용하여 return타입 boolean으로 변경하고
	// 예약한 경우 인적사항으로 검색하여 나온 값과 같은 방이 있으면 체크인
	// 05-10-2023 21:00_주하태 논리 합침
	// 05-13 2023 18:00_성지수 체크인 여러번 가능한 오류 수정
	public boolean findCustomer2(String nameOrPhoneNumber) {
		nameOrPhoneNumber = customerManagement.removeHyphen(nameOrPhoneNumber);

		for (int i = 0; i < h.roomsLength(); i++) {
			for (int j = 0; j < h.roomsLength(i); j++) {
				if (h.whoInTheRoom(i, j) != null) {
					if (nameOrPhoneCollect(nameOrPhoneNumber, i, j)) {
						if (!(h.isCheckInRoom(i, j))) {
							h.checkInIndex(h.getRoomNumber(i, j));
							return true;
						} else {
							return false;
						}
					}
				}
			}
		}
		return false;
	}

	// 1. 객실 현황
	public void menu1() {
		while (true) {
			System.out.println("1. 전체 객실 현황\n2. 객실 상태별 확인\n3. 뒤로가기");
			int select = inputData.userInputNumber();
			switch (select) {
			case 1:
				h.ShowTotalRooms();
				return;
			case 2:
				roomConditionCheck();
				return;
			case 3:
				return;
			}
		}
	}

	// 05-11-2023 18:20 장명근 예약 기간 확인 추가
	// 05-12-2023 01:12 성지수 예약할때 체크인 날짜, 숙박일수 입력하고 체크아웃 날짜 출력
	// 05-12-2023 12:00 장명근 날짜형식 안맞을시 예외처리 추가
	// 05-14-2023 02:08 성지수 예외시 반복 추가
	public int checkInOutDay(int num) {
		while (true) {
			LocalDate inDay = LocalDate.now();
			boolean b = false;
			System.out.println("체크인 날짜를 입력 (yyyyMMdd)");
			while (!b) {
				String checkInDay = inputData.userInputString();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
				try {
					inDay = LocalDate.parse(checkInDay, formatter);
					b = true;
				} catch (DateTimeParseException e) {
					System.out.println("잘못된 형식입니다. 다시 입력하세요.");
				}
			}

			if (inDay.isAfter(LocalDate.now()) || inDay.isEqual(LocalDate.now())) {
				System.out.println("숙박 일수 입력");
				while (true) {
					int checkOutDay = inputData.userInputNumber();

					if (checkOutDay > 0) {
						LocalDate outDay = inDay.plusDays(checkOutDay);
						System.out.println("체크아웃 날짜: " + outDay);
						int[] i = h.divideRoomNum(num);
						h.roomArray_B[i[0]][i[1]].setCheckInDay(inDay);
						h.roomArray_B[i[0]][i[1]].setCheckOutDay(outDay);
						
						return checkOutDay;
					} else {
						System.out.println("1 이상의 정수를 입력하세요.");
					}
				}
			} else {
				System.out.println("오늘 이후의 날짜로 입력하세요.");
			}
		}
	}

	// 05-11-2023 21:41 성지수 체크아웃 시 출력문구
	public void toDayOut() {
		System.out.println("체크아웃 시간: " + LocalDate.now() + " " + LocalTime.now());
	}

	// 05-13-2023 21:35 성지수 예약, 예약 안하고 체크인 시 금액계산
	public void payRoom(Person person, int day, int num) {
		if (customerManagement.identifyVIP2(person)) {
			if (num % 2 != 0) {
				printPayPriceVIP(day, Price_B.SINGLE_PRICE, h.roomType(num), num);
			} else {
				printPayPriceVIP(day, Price_B.DOUBLE_PRICE, h.roomType(num), num);
			}
		} else {
			if (num % 2 != 0) {
				printPayPrice(day, Price_B.SINGLE_PRICE, h.roomType(num), num);
			} else {
				printPayPrice(day, Price_B.DOUBLE_PRICE, h.roomType(num), num);
			}
		}
	}

	// 05-13-2023 22:58 성지수 금액 계산 메소드 새로 작성, 수정
	public void printPayPriceVIP(int day, int roomPrice, String roomtype, int num) {
		int price = (int) (day * (roomPrice -(roomPrice * VIP_Customer.DISCOUNT_RATE)));
		System.out.println("VIP고객 확인");
		System.out.printf("%s 1박당 기본 %d원 + %.0f %% 할인\n", roomtype, roomPrice, VIP_Customer.DISCOUNT_RATE * 100);
		System.out.printf("%d호 총 금액 : %d원\n", num, price);
	}

	public void printPayPrice(int day, int roomPrice, String roomtype, int num) {
		int price = day * roomPrice;
		System.out.printf("%s 1박당 %d원\n", roomtype, roomPrice);
		System.out.printf("%d호 총 금액 : %d원\n", num, price);
	}

	// 방 청소 메뉴
	public void cleaningRoom() {
		while (true) {
			System.out.println("1. 특정 객실 청소\n2. 모든 객실 청소\n3. 뒤로가기");
			int num = inputData.userInputNumber();
			switch (num) {
			case 1:
				System.out.println("청소할 객실 번호 입력");
				h.cleaningRoom(inputData.userInputRoomNumber());
				return;
			case 2:
				h.allCleanRoom();
				return;
			case 3:
				return;
			}

		}
	}
}
