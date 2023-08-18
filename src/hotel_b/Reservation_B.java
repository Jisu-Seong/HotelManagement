package hotel_b;

import customer.CustomerManagement;
import customer.Person;
import customer.VIP_Customer;
import hotel.Room;

public class Reservation_B {
	public static PresentationRoom_B presentationRoom = new PresentationRoom_B();
	public static HotelManagement_B management = new HotelManagement_B();
	public static CustomerManagement customerManagement = new CustomerManagement();
	public static InputData_B inputData = new InputData_B();

	// 예약 관리
	public void reservation() {
		while (true) {
			System.out.println("1. 예약하기\n2. 예약취소\n3. 뒤로가기");
			int choice2 = inputData.userInputNumber();
			switch (choice2) {
			case 1:
				doReservation();
				return;
			case 2:
				System.out.println("객실 번호 입력");
				int num2 = inputData.userInputRoomNumber();
				cancelReservation(num2);
				return;
			case 3:
				return;
			}
		}
	}

	// 05-11-2023 12:14_성지수 예약메소드 변경 reservation case1
	// 05-13-2023 21:45_성지수 날짜, 호수 추가
	public void doReservation() {
		System.out.println("객실 번호 입력");
		int num = inputData.userInputRoomNumber();
		if (management.identifyEmptyRoom(num)) {

			System.out.println("고객의 이름과 전화번호 입력");
			Person person = customerManagement.userInputPerson();
			person = customerManagement.identifyVIP(person);

			int day = presentationRoom.checkInOutDay(num);
			if (day == 0) {
				System.out.println("예약을 중지합니다.");
			} else {
				presentationRoom.payRoom(person, day, num);
				management.reservationIndex(person, num);
			}

		} else {
			System.out.println("예약할 수 없는 객실입니다.\n다른 객실 번호를 입력하세요.");
		}

	}

	// 2. 예약취소
	public void cancelReservation(int num) {
		int i[] = management.divideRoomNum(num);

		if (management.whoInTheRoom(i[0], i[1]) == null) {
			System.out.println("빈 방입니다.");
		} else {
			management.roomArray_B[i[0]][i[1]] = new Room();
			System.out.println("예약을 취소하였습니다.");
		}
	}

}
