package hotel;

import java.time.LocalDate;

import customer.Person;

public class Room {
	Person person;
	boolean emptyRoom;
	boolean reservationRoom;
	boolean checkInRoom;
	boolean clean;
	LocalDate checkInDay;
	LocalDate checkOutDay;

	public Room() {
		emptyRoom = true;
		clean = true;
	}

	public Room(Person person, boolean emptyRoom, boolean reservationRoom, boolean checkInRoom) {
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

	public LocalDate getCheckInDay() {
		return checkInDay;
	}

	public void setCheckInDay(LocalDate checkInDay) {
		this.checkInDay = checkInDay;
	}

	public LocalDate getCheckOutDay() {
		return checkOutDay;
	}

	public void setCheckOutDay(LocalDate checkOutDay) {
		this.checkOutDay = checkOutDay;
	}

}
