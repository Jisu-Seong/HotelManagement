package customer;

import java.util.Objects;

public class Person {
	private String name;
	private String phoneNumber;

	public Person(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "이름 - " + name + " / 전화번호 - " + phoneNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null) {
			return false;
		}
		Person other = (Person) obj;
		return Objects.equals(name, other.name) && Objects.equals(phoneNumber, other.phoneNumber);
	}

}
