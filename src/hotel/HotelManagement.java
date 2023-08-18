package hotel;

import java.util.Random;
import java.util.function.Predicate;

import customer.CustomerManagement;
import customer.Person;

public class HotelManagement {
	static public Room[][] roomArray = new Room[4][20];
	public static Price price = new Price();
	public static CustomerManagement customerManagement = new CustomerManagement();
	public static InputData inputData = new InputData();

	// 전부 빈 방으로 초기화
	public void resetRooms() {
		for (int i = 0; i < roomsLength(); i++) {
			for (int j = 0; j < roomsLength(i); j++) {
				roomArray[i][j] = new Room();
			}
		}
	}

	// 05-11-2023 11:57 _주하태 메소드병합
	public boolean isEmptyRoom(int floor, int num) {
		return roomArray[floor][num].isEmptyRoom();
	}

	public boolean isCleanRoom(int floor, int num) {
		return roomArray[floor][num].isClean();
	}

	public boolean isCheckInRoom(int floor, int num) {
		return roomArray[floor][num].isCheckInRoom();
	}

	public boolean isReservationRoom(int floor, int num) {
		return roomArray[floor][num].isReservationRoom();
	}

	// 기능추가 2023-05-10 방 안에 누구?.주하태
	public Person whoInTheRoom(int floor, int num) {
		return roomArray[floor][num].getPerson();
	}

	// setter
	public void setEmptyRoom(int floor, int num, boolean condition) {
		roomArray[floor][num].setEmptyRoom(condition);
	}

	public void setCleanRoom(int floor, int num, boolean condition) {
		roomArray[floor][num].setClean(condition);
	}

	public void setCheckInRoom(int floor, int num, boolean condition) {
		roomArray[floor][num].setCheckInRoom(condition);
	}

	public void setReservationRoom(int floor, int num, boolean condition) {
		roomArray[floor][num].setReservationRoom(condition);
	}

	// 기능 추가 roomArray.length2023-05-10 .주하태
	public int roomsLength() {
		return roomArray.length;
	}

	public int roomsLength(int floor) {
		return roomArray[floor].length;
	}

	// 05-11-2023 11:42 _주하태 방 번호 나누기
	public int[] divideRoomNum(int num) {
		int[] divideNum = new int[2];
		divideNum[0] = convertfloorIndex(num);
		divideNum[1] = convertRoomNumIndex(num);
		return divideNum;
	}

	// 입력한 방번호를 인덱스 숫자로 변경(방 층수)
	public int convertfloorIndex(int num) {
		int floorIndex = num / 100 - 2;
		return floorIndex;
	}

	// (방 호수)
	public int convertRoomNumIndex(int num) {
		int roomNumIndex = num % 100 - 1;
		return roomNumIndex;
	}

	// 2023-05-10 .주하태
	// 방번호 얻기
	public int getRoomNumber(int floor, int num) {
		int roomNumber = (floor + 2) * 100 + (num + 1);
		return roomNumber;
	}

	// 방 타입 리턴(홀/짝) X
	public String roomType(int num) {
		if (num % 2 == 0)
			return "더블";
		return "싱글";
	}

	// 예약가능한 방 번호인지 확인
	// 05-11-2023 21:15_주하태 if문 축소
	public boolean identifyEmptyRoom(int num) {
		int[] i = divideRoomNum(num);
		if (isEmptyRoom(i[0], i[1]))
			return true;
		return false;
	}

	// 인덱스로 방 정보 변경
	public void changeRoomCondition(int num, Room room) {
		int[] i = divideRoomNum(num);
		roomArray[i[0]][i[1]] = room;
	}

	// 방의 표시 결정(OX)
	// 05-11-2023 21:13 주하태 True 제거 및 !연산자 사용
	// 05-11-2023 21:13 주하태 if문 축소 및 논리 축소
	public String stateRoom(int i, int j) {
		if (isEmptyRoom(i, j) && !(isCleanRoom(i, j)))
			return "●";
		if (isEmptyRoom(i, j) && !(isCleanRoom(i, j)))
			return "○";
		if (isReservationRoom(i, j))
			return "△";
		if (isCheckInRoom(i, j))
			return "X";
		return "○";
	}

	// 05-13-2023_20:45 성지수 구분선 추가 메소드
	public void divisionLine() {
		System.out.print("-----------------------------------------");
		System.out.print("-----------------------------------------\n");
	}

	// 전체 방 배열 보여주기
	public void ShowTotalRooms() {
		System.out.print("빈 객실 : ○ / 예약된 객실 : △  / 사용중인 객실 : X / 청소 중인 객실 : ●");
		System.out.print("\t홀수 객실 : 싱글 / 짝수 객실 : 더블\n");
		divisionLine();
		for (int i = 0; i < roomsLength(); i++) {
			for (int j = 0; j < roomsLength(i); j++) {
				System.out.printf("%d\t", getRoomNumber(i, j));
			}
			System.out.println();
			for (int j = 0; j < roomsLength(i); j++) {
				System.out.print(stateRoom(i, j) + "\t");
			}
			System.out.println();
		}
	}

	// 05-10-2023 주하태 기능수정-방 보여주기
	public void printRoomNumAndCustomer(int i, int j) {
		System.out.printf("    %d\t\t", getRoomNumber(i, j));
		System.out.println(whoInTheRoom(i, j));
	}

	public void showEmptyRoom() {
		int emptyRoom = showRoom(Room::isEmptyRoom);
		System.out.println("빈 객실 개수 : " + emptyRoom);
		divisionLine();
	}

	public void showCheckInRoom() {
		int checkInRoom = showRoom(Room::isCheckInRoom);
		System.out.println("사용중인 객실 개수 : " + checkInRoom);
		divisionLine();
	}

	public void showReservationRoom() {
		int reservationRoom = showRoom(Room::isReservationRoom);
		System.out.println("예약된 객실 개수 : " + reservationRoom);
		divisionLine();
	}

	public int showRoom(Predicate<Room> roomState) {
		int count = 0;
		for (int i = 0; i < roomsLength(); i++) {
			for (int j = 0; j < roomsLength(i); j++) {
				if (roomState.test(roomArray[i][j])) {
					printRoomNumAndCustomer(i, j);
					count++;
				}
			}
		}
		return count;
	}

	// 05-11-2023 13:11_주하태 추가 이름찾기
	public boolean sameName(String name, int i, int j) {
		return name.equals(whoInTheRoom(i, j).getName());
	}

	// 05-11-2023 13:11_주하태 추가 번호찾기
	public boolean sameNum(String number, int i, int j) {
		return number.equals(whoInTheRoom(i, j).getPhoneNumber());
	}

	public Person samePerson(String string) {
		for (int i = 0; i < roomsLength(); i++) {
			for (int j = 0; j < roomsLength(i); j++) {
				Person currentPerson = whoInTheRoom(i, j);
				if (currentPerson == null)
					continue;
				if (sameName(string, i, j) || sameNum(string, i, j))
					return currentPerson;
			}
		}
		return null;
	}

	// 05-11-2023 12:37_성지수 예약boolean true로 바꾸는 메소드
	public void reservationIndex(Person person, int num) {
		int[] i = divideRoomNum(num);
		roomArray[i[0]][i[1]].setPerson(person);
		setEmptyRoom(i[0], i[1], false);
		setReservationRoom(i[0], i[1], true);
		System.out.println("예약 완료");
	}

	// checkIn
	// 05-11-2023 16:28 성지수 예약한 사람의 호실로만 체크인 수정
	public void checkInPerson(int num, Person person) {
		int[] i = divideRoomNum(num);
		checkInIndex(num);
		roomArray[i[0]][i[1]].setPerson(person);
	}

	public void checkInIndex(int num) {
		int[] i = divideRoomNum(num);
		setCheckInRoom(i[0], i[1], true);
		setEmptyRoom(i[0], i[1], false);
		setReservationRoom(i[0], i[1], false);
		System.out.println("체크인 완료");
	}

	public void checkOutIndex(int num) {
		int[] i = divideRoomNum(num);
		setCheckInRoom(i[0], i[1], false);
		setEmptyRoom(i[0], i[1], true);
		setCleanRoom(i[0], i[1], false);
		roomArray[i[0]][i[1]].setPerson(null);
		System.out.println("체크아웃 완료");
	}

	// 05-11-2023 11:17_성지수 HotelManagement 청소메소드 추가
	public void cleaningRoom(int num) {
		int[] i = divideRoomNum(num);
		if (!(isCleanRoom(i[0], i[1]))) {
			setCleanRoom(i[0], i[1], true);
			System.out.println("객실 청소 완료");
		} else {
			System.out.println("이미 청소된 객실입니다.");
		}
	}

	// 방번호 호출
	public int whereIsCustomer(String numOrName) {
		numOrName = customerManagement.removeHyphen(numOrName);
		int roomNum = 0;
		for (int i = 0; i < roomsLength(); i++) {
			for (int j = 0; j < roomsLength(i); j++) {
				if (whoInTheRoom(i, j) == null)
					continue;
				if (sameName(numOrName, i, j) || sameNum(numOrName, i, j))
					roomNum = getRoomNumber(i, j);
			}
		}
		return roomNum;
	}

	// 방의 청소상태 랜덤
	public void ranCleanCondition() {
		Random r = new Random();
		for (int i = 0; i < roomsLength(); i++) {
			for (int j = 0; j < roomsLength(i); j++) {
				int ranCleanNum = r.nextInt(10);
				if (ranCleanNum % 3 == 0)
					setCleanRoom(i, j, false);
				else
					setCleanRoom(i, j, true);
			}
		}
	}

	// 방 전부 청소
	public void allCleanRoom() {
		for (int i = 0; i < roomsLength(); i++) {
			for (int j = 0; j < roomsLength(i); j++) {
				setCleanRoom(i, j, true);
			}
		}
		System.out.println("모든 객실 청소 완료");
	}

	public void printCheckDay(int i, int j) {
		System.out.println("체크인 날짜: " + roomArray[i][j].getCheckInDay());
		System.out.println("체크아웃 날짜: " + roomArray[i][j].getCheckOutDay());
	}

}