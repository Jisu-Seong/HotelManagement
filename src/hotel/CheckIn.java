//05-11-2023 11:22 _주하태 클래스 생성
package hotel;

import customer.CustomerManagement;
import customer.Person;

public class CheckIn {
	public static HotelManagement hotelManagement = new HotelManagement();
	public static PresentationRoom presentationRoom = new PresentationRoom();
	public static CustomerManagement customerManagement = new CustomerManagement();
	public static Price price = new Price();
	public static InputData inputData = new InputData();
	Reservation reservation = new Reservation();

	public Room hotelRoom(int i, int j) {
		return hotelManagement.roomArray[i][j];
	}

	// 05-11-2023 16:35 성지수 체크인 프로그램 예약 안한 사람 체크인 할 수 있게 수정
	public void checkin() {
		while (true) {
			System.out.println("1. 체크인\n2. 체크아웃\n3. 뒤로가기");
			switch (inputData.userInputNumber()) {
			case 1:
				loopCheckReservation();
				return;
			case 2:
				doCheckOut();
				presentationRoom.toDayOut();
				return;
			case 3:
				return;
			}
		}
	}

	// 05-11-2023 23:20 _주하태 checkIn method 분리
	public boolean nameOrPhoneNumInPut(boolean b) {
		Person person;
		int roomNum;
		System.out.println("고객의 이름 혹은 전화번호로 입력");
		String userInput = inputData.userInputString();
		if (!presentationRoom.findCustomer2(userInput)) {
			System.out.println("다시 한 번 확인해주세요.");
//			return true;
		} else {
			person = hotelManagement.samePerson(userInput);
			roomNum = hotelManagement.whereIsCustomer(userInput);
//			return false;
		}
		return false;
	}

	// 예약 메뉴
	public void loopCheckReservation() {
		boolean b = true;
		while (b) {
			System.out.println("예약을 하셨습니까? 1. 예 2. 아니요");
			int num2 = inputData.userInputNumber();
			if (num2 != 1 && num2 != 2)
				System.out.println("다시 입력해주세요.");
			if (num2 == 1)
				b = nameOrPhoneNumInPut(b);
			if (num2 == 2)
				b = doCheckIn();

		}
	}

	// 05-13-2023 21:45_성지수 예약 안한 사람 체크인 메소드 수정(날짜, 호수 추가)
	public boolean doCheckIn() {
		System.out.println("객실 번호 입력");
		int num = inputData.userInputRoomNumber();
		if (hotelManagement.identifyEmptyRoom(num)) {

			System.out.println("고객의 이름과 전화번호 입력");
			Person person = customerManagement.userInputPerson();
			person = customerManagement.identifyVIP(person);

			// doReservation 메소드와의 차이는 아래줄의 checkInNowDate()
			int day = presentationRoom.checkInNowDate(num);

			presentationRoom.payRoom(person, day, num);
			hotelManagement.checkInPerson(num, person);
			return false;
		} else {
			System.out.println("체크인할 수 없는 객실입니다.\n다른 객실을 입력하세요.");
			return true;
		}
	}

	// 체크아웃
	public void doCheckOut() {
		System.out.println("객실 번호 입력");
		int roomNum = inputData.userInputRoomNumber();
		int[] i = hotelManagement.divideRoomNum(roomNum);
		if (hotelManagement.isEmptyRoom(i[0], i[1]))
			System.out.println("빈 객실입니다.");
		else
			hotelManagement.checkOutIndex(roomNum);
	}

}
