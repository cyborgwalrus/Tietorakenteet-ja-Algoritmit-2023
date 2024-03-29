package oy.interact.tira.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Comparable.html#compareTo(T)
 * It is strongly recommended, but not strictly required that
 * (x.compareTo(y)==0) == (x.equals(y)).
 * Generally speaking, any class that implements the Comparable interface and
 * violates this condition
 * should clearly indicate this fact. The recommended language is "Note: this
 * class has a natural
 * ordering that is inconsistent with equals."
 */
public class Coder implements Comparable<Coder> {
	private String id;
	private String lastName;
	private String firstName;
	private String coderName;
	private String phoneNumber;

	// For Fibonacci hashing, ~= 2^30 * golden ratio,
	// should be 2^32 but it wouldn't fit into int
	// https://en.wikipedia.org/wiki/Hash_function#fibonacci_hashing
	private final int FIBONACCHI_MULTIPLIER = 1737350767;

	/**
	 * Only to be used when reading objects from JSON or other
	 * storage/source. When creating new Coders, id should be
	 * randomly generated by using the other constructor.
	 * 
	 * @param id The already created ID for the Coder.
	 */
	public Coder(String id) {
		this.id = id;
	}

	public Coder(String firstName, String lastName, String phoneNumber) {
		id = UUID.randomUUID().toString();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}

	public String getCoderName() {
		if (null != coderName) {
			return coderName;
		}
		return "";
	}

	public void setCoderName(String coderName) {
		this.coderName = coderName;
	}

	public String getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getFullName() {
		return lastName + " " + firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	private Set<String> languages;
	private Set<String> friendIDs;

	public boolean hasLanguages() {
		if (null != languages) {
			return !languages.isEmpty();
		}
		return false;
	}

	public void addLanguage(String language) {
		if (null == languages) {
			languages = new HashSet<>();
		}
		languages.add(language);
	}

	public Set<String> getLanguages() {
		return languages;
	}

	public String getLanguagesString() {
		if (null != languages && !languages.isEmpty()) {
			StringBuilder builder = new StringBuilder();
			int counter = 0;
			for (String language : languages) {
				builder.append(language);
				if (counter < languages.size() - 1) {
					builder.append(", ");
				}
				counter++;
			}
			return builder.toString();
		}
		return "";
	}

	public boolean knowsLanguage(String language) {
		if (null != languages) {
			return languages.contains(language);
		}
		return false;
	}

	public boolean hasFriends() {
		if (null != friendIDs) {
			return !friendIDs.isEmpty();
		}
		return false;
	}

	public void addFriend(String friendID) {
		if (null == friendIDs) {
			friendIDs = new HashSet<>();
		}
		friendIDs.add(friendID);
	}

	public Set<String> getFriendIDs() {
		return friendIDs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(lastName);
		builder.append(" ");
		builder.append(firstName);
		builder.append(" (");
		builder.append(coderName);
		builder.append(")");
		return builder.toString();
	}

	/**
	 * Note: this class has a natural ordering that is inconsistent with equals.
	 * 
	 * Equals compares the equality of the Coder.id, as compareTo compares
	 * the Coder full names. This is to make sure we can hold several coders
	 * in containers having the same name (as happens in real world), but still
	 * can identify them as different Coders.
	 */
	@Override
	public boolean equals(Object another) {
		if (this == another) {
			return true;
		}
		if (null == another) {
			return false;
		}
		if (another.getClass() != this.getClass()) {
			return false;
		}
		return id.equals(((Coder) another).id);
	}

	////////////////////////////////////////////////////////////////////////////////////////////
	// Students: implement the two methods below following the instructions
	//////////////////////////////////////////////////////////////////////////////////////////// _carefully_!
	// Expecially see the note in the class comment above, and the comments below.
	////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Note: this class has a natural ordering that is inconsistent with equals.
	 * 
	 * Equals compares the equality of the Coder.id, as compareTo compares
	 * the Coder full names. This is to make sure we can hold several coders
	 * in containers having the same name (as happens in real world), but still
	 * can identify them as different Coders using the Coder.id.
	 * 
	 * Implement compareTo so that the order of coders ordered by using this methos
	 * will be natural order. Meaning, alphabetical order A...Ö (by
	 * lastname-firstname order).
	 */

	private int compareLastNames(Coder another) {
		return this.getLastName().compareTo(another.getLastName());
	}

	private int compareFirstNames(Coder another) {
		return this.getFirstName().compareTo(another.getFirstName());
	}

	@Override
	public int compareTo(Coder another) {
		int result = compareLastNames(another);
		if (result == 0) {
			return compareFirstNames(another);
		}
		return result;

	}

	/**
	 * You need to implement this in Exercise 8 on hash tables. No need to implement
	 * this before!
	 * 
	 * Students (task 8): Calculate the hash for the Coder. In this case, the
	 * hash must be related
	 * to the unique identity of the coder. Since coders can have a same full name,
	 * calculate the hash from the permanent id of the Coder, which does not change.
	 * 
	 * @return The hash calculated from the id of the Coder.
	 */
	@Override
	public int hashCode() {
		// Calculate the hash value using Fibonacchi hashing
		// https://probablydance.com/2018/06/16/fibonacci-hashing-the-optimization-that-the-world-forgot-or-a-better-alternative-to-integer-modulo/
		
		// Split the first 30 digits of UUID into 6 pieces, then xor them together
		String id = this.getId().replace("-", "");
		int xorSum = 0;
		for(int i = 0; i + 6 < 32; i += 5){
			xorSum = xorSum ^ Integer.parseInt(id.substring(i, i + 5), 16);
		}

		// Multiply the xorSum with the fibonacchi hash multiplier, letting the int overflow
		return xorSum * FIBONACCHI_MULTIPLIER;
	}

}
