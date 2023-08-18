//05-11-2023 11:22 _주하태 클래스 생성
package hotel_b;

import customer.CustomerManagement;
import customer.Person;
import hotel.Room;

public class CheckIn_B {
	public static HotelManagement_B hotelManagement_B = new HotelManagement_B();
	public static PresentationRoom_B presentationRoom_B = new PresentationRoom_B();
	public static CustomerManagement customerManagement = new CustomerManagement();
	public static Price_B price_B = new Price_B();
	public static InputData_B inputData_B = new InputData_B();
	Reservation_B reservation_B = new Reservation_B();

	public Room hotelRoom(int i, int j) {
		return hotelManagement_B.roomArray_B[i][j];
	}

	// 05-14-2023 00:29 전부 예약제
	public void checkin() {
		while (true) {
			System.out.println("1. 체크인\n2. 체크아웃\n3. 뒤로가기");
			switch (inputData_B.userInputNumber()) {
			case 1:
				if (!(loopCheckReservation())) {
					System.out.println("이 호텔 건물은 전실 예약제입니다.");
				} else {
					nameOrPhoneNumInPut();
				}
				return;
			case 2:
				doCheckOut();
				presentationRoom_B.toDayOut();
				return;
			case 3:
				return;
			}
		}
	}

	// 05-11-2023 23:20 _주하태 checkIn method 분리
	public boolean nameOrPhoneNumInPut() {
		Person person;
		int roomNum;
		System.out.println("고객의 이름 혹은 전화번호로 입력");
		String userInput = inputData_B.userInputString();
		if (!presentationRoom_B.findCustomer2(userInput)) {
			System.out.println("다시 한 번 확인해주세요.");
			return true;
		} else {
			person = hotelManagement_B.samePerson(userInput);
			roomNum = hotelManagement_B.whereIsCustomer(userInput);
			return false;
		}
	}

	// 05-14-2023 00:51 성지수 예약 했는지 묻고 안했으면 내쫒기
	public boolean loopCheckReservation() {
		while (true) {
			System.out.println("예약을 하셨습니까? 1. 예 2. 아니요");
			int num2 = inputData_B.userInputNumber();
			if (num2 != 1 && num2 != 2)
				System.out.println("다시 입력해주세요.");
			else if (num2 == 1)
				return true;
			else
				return false;
		}
	}

	// 체크아웃
	public void doCheckOut() {
		System.out.println("객실 번호 입력");
		int roomNum = inputData_B.userInputRoomNumber();
		int[] i = hotelManagement_B.divideRoomNum(roomNum);
		if (hotelManagement_B.isEmptyRoom(i[0], i[1]))
			System.out.println("빈 객실입니다.");
		else
			hotelManagement_B.checkOutIndex(roomNum);
	}

}
