package customer;

import hotel.HotelManagement;
import hotel.InputData;
import hotel.PresentationRoom;

public class CustomerManagement {
	private static final int MAXIMUN_VIP = 10;
	private static VIP_Customer[] vip_Customers = new VIP_Customer[MAXIMUN_VIP];
	private static int curruntNum = 0;
	public static PresentationRoom presentationRoom = new PresentationRoom();
	public static HotelManagement hotelManagement = new HotelManagement();
	public static InputData inputData = new InputData();

	// 05-10-2023 주하태 하이픈 제거기
	public String removeHyphen(String number) {
		return number.replace("-", "");
	}

	// 사람 데이터 입력, Person 리턴
	public Person userInputPerson() {
		String name = inputData.userInputString();
		String phoneNum = inputData.userInputString();
		phoneNum = removeHyphen(phoneNum);
		return new Person(name, phoneNum);
	}

	// vip 추가
	public void addVIP(VIP_Customer vip) {
		if (curruntNum < 10) {
			vip_Customers[curruntNum] = vip;
			curruntNum++;
		} else {
			System.out.println("VIP를 더이상 추가할 수 없습니다.");
		}
	}

	// 05-10-2023 주하태 vip등록, 기능 추가
	public void userInputVIP() {
		System.out.println("고객의 이름과 전화번호 입력");
		Person person = userInputPerson();
		VIP_Customer vip = new VIP_Customer(person.getName(), person.getPhoneNumber());
		addVIP(vip);
		System.out.println("vip 등록완료");

	}

	// 05-10-2023 주하태 getter추가
	public VIP_Customer[] getVip_Customers() {
		return vip_Customers;
	}

	// 05-11-2023 12:33_성지수 person이 vip인지 확인후 리턴
	// 05-11-2023 21:09_주하태 if문 축소
	public Person identifyVIP(Person person) {
		VIP_Customer[] vip_Customers = getVip_Customers();
		for (int i = 0; i < vip_Customers.length; i++) {
			if (vip_Customers[i] != null && person.equals(vip_Customers[i])) {
				return vip_Customers[i];
			}
		}
		return person;
	}

	public boolean identifyVIP2(Person person) {
		VIP_Customer[] vip_Customers = getVip_Customers();
		for (int i = 0; i < vip_Customers.length; i++) {
			if (vip_Customers[i] != null && person.equals(vip_Customers[i])) {
				return true;
			}
		}
		return false;
	}

}
