package hotel;

import customer.CustomerManagement;
import customer.Person;
import customer.VIP_Customer;

// 05-12-2023 주하태 객실 가격 계산기 작성

public class Price {
	
	public static HotelManagement hotelManagement = new HotelManagement();
	public static CustomerManagement customerManagement = new CustomerManagement();
	
	public final static int SINGLE_PRICE = 50000;
	public final static int DOUBLE_PRICE = 80000;
	
	// 05-10-2023 주하태 방 가격 설정 추가
	public int setRoomPrice(int num) {
		int price = 0;
		if (hotelManagement.roomType(num).equals("싱글")) price = SINGLE_PRICE;
		if (!(hotelManagement.roomType(num).equals("싱글"))) price = DOUBLE_PRICE;
		return price;
	}

	// 05-10-2023 주하태 방 가격 출력문 추가
	public void calRoomPrice(int roomNumber) {
		String roomType = hotelManagement.roomType(roomNumber);
		int price = setRoomPrice(roomNumber);
		System.out.println(roomNumber + "호 (" + roomType + ") : " + (price) + "원");
	}
	
	public void calRoomPriceVIP(int roomNumber) { //int 
		String roomType = hotelManagement.roomType(roomNumber);
		int price = setRoomPrice(roomNumber);
		System.out.println(roomNumber + "호 (" + roomType + ") : " + (int)((price-(price*VIP_Customer.DISCOUNT_RATE))) + "원");
	}
	
	public boolean checkVIP(Person person) {
		VIP_Customer[] vip_Customers = customerManagement.getVip_Customers();
		System.out.println(person.getName());
		for (int i = 0; i < vip_Customers.length; i++) {
			if (vip_Customers[i] != null && person.equals(vip_Customers[i]) ) return true;
		}
		return false;
	}
	
	public void printPrice(Person person, int num) {
		if (checkVIP(person)) {
			calRoomPriceVIP(num);
		} else {
			calRoomPrice(num);
		}
	}
}
