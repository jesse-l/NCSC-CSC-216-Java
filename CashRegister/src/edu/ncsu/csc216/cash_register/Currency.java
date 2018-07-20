package edu.ncsu.csc216.cash_register;

/**
 * Represents a bill or coin that is a currency
 * @author SarahHeckman
 * @author jesse liddle - jaliddl2
 */
public class Currency {
	
	/**
	 * Monetary value of the currency
	 */
	private int value;
	/**
	 * Name of the currency
	 */
	private String name;
	/**
	 * Number of currency
	 */
	private int count;
	
	/**
	 * Constructs a currency object
	 * @param value of the currency
	 * @param name of the currency
	 * @param count of the currency
	 */
	public Currency(int value, String name, int count) {
		this.value = value;
		this.name = name;
		this.count = count;
	}
	
	/**
	 * Returns the currency's value
	 * @return the currency's value
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Returns the currency's name
	 * @return the currency's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the currency's count
	 * @return the currency's count
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * Modifies the count by the given amount.  The provide amount can be positive
	 * or negative.  If the count for the Currency would be negative
	 * due to the change, an IllegalArgumentException is thrown.
	 * @param amount the amount to modify the currency count by.  The amount could
	 * be positive or negative.
	 * @throws IllegalArgumentException if the resulting count would be negative
	 */
	public void modifyCount(int amount) {
		this.count += amount;
	}

	/**
	 * Returns the hash code for the current object
	 * @return the hash code for the current object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + value;
		return result;
	}

	/**
	 * Returns true if the given object is the same as the current object.
	 * @param obj object to compare with current object
	 * @return true if the given object is the same as the current object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Currency other = (Currency) obj;
		if (count != other.count)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (value != other.value)
			return false;
		return true;
	}

}
