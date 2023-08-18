package hotel_b;

import customer.Person;

public class Room_B {
	Person person;
	boolean emptyRoom;
	boolean reservationRoom;
	boolean checkInRoom;
	boolean clean;

	public Room_B() {
		emptyRoom = true;
		clean = true;
	}

	public Room_B(Person person, boolean emptyRoom, boolean reservationRoom, boolean checkInRoom) {
		this.person = person;
		this.emptyRoom = emptyRoom;
		this.reservationRoom = reservationRoom;
		this.checkInRoom = checkInRoom;
		this.clean = true;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public boolean isEmptyRoom() {
		return emptyRoom;
	}

	public void setEmptyRoom(boolean emptyRoom) {
		this.emptyRoom = emptyRoom;
	}

	public boolean isReservationRoom() {
		return reservationRoom;
	}

	public void setReservationRoom(boolean reservationRoom) {
		this.reservationRoom = reservationRoom;
	}

	public boolean isCheckInRoom() {
		return checkInRoom;
	}

	public void setCheckInRoom(boolean checkInRoom) {
		this.checkInRoom = checkInRoom;
	}

	public boolean isClean() {
		return clean;
	}

	public void setClean(boolean clean) {
		this.clean = clean;
	}

}
