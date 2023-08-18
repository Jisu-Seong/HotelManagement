package customer;

public class VIP_Customer extends Person {
	public static final String FONT_YELLOW = "\u001B[33m";
	public static final String RESET = "\u001B[0m";
	public static final double DISCOUNT_RATE = 0.1;

	public VIP_Customer(String name, String phoneNumber) {
		super(name, phoneNumber);
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	public String getPhoneNumber() {
		return super.getPhoneNumber();
	}

	@Override
	public void setPhoneNumber(String phoneNumber) {
		super.setPhoneNumber(phoneNumber);
	}
	

	public double getDiscountRate() {
		return DISCOUNT_RATE;
	}

	@Override
	public String toString() {
		return FONT_YELLOW + super.toString() + " VIP" + RESET;
	}

}
