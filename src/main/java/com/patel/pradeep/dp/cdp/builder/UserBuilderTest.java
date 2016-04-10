/**
 *  http://howtodoinjava.com/design-patterns/creational/builder-pattern-in-java/
 */
package com.patel.pradeep.dp.cdp.builder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author prade
 *
 */
public class UserBuilderTest {

	public static void main(String[] args) {
		/*User user1 = new User.UserBuilder("Lokesh", "Gupta").age(30).phone("1234567").address("Fake address 1234")
				.build();

		System.out.println(user1);*/

		User user2 = new User.UserBuilder("Jack", "Reacher").age(40).phone("5655")
				// no address
				.build();

		System.out.println(user2);

		User user3 = new User.UserBuilder("Super", "Man").address("mumbai")
				// No age
				// No phone
				.build();

		System.out.println(user3);
		user3.print();
	}

}

class User {
	// All final attributes
	private final String firstName; // required
	private final String lastName; // required
	private final int age; // optional
	private final String phone; // optional
	private final String address; // optional

	private User(UserBuilder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.age = builder.age;
		this.phone = builder.phone;
		this.address = builder.address;
	}

	// All getter, and NO setter to provde immutability
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "User: " + this.firstName + ", " + this.lastName + ", " + this.age + ", " + this.phone + ", "
				+ this.address;
	}
	
	public void print(){
		Method[] methods = User.class.getMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("get") && method.getGenericParameterTypes().length == 0) {
            	System.out.println("GEN->"+method.getGenericParameterTypes().getClass().getName());
                Object returnObject;
				try {
					returnObject = method.invoke(this);
					 System.out.println("PPPP->"+method.getName() + " - " + returnObject+" TYPE->"+method.getReturnType());
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
	}

	public static class UserBuilder {
		private final String firstName;
		private final String lastName;
		private int age;
		private String phone;
		private String address;

		public UserBuilder(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public UserBuilder age(int age) {
			this.age = age;
			return this;
		}

		public UserBuilder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public UserBuilder address(String address) {
			this.address = address;
			return this;
		}

		// Return the finally consrcuted User object
		public User build() {
			User user = new User(this);
			validateUserObject(user);
			return user;
		}

		private void validateUserObject(User user) {
			// Do some basic validations to check
			// if user object does not break any assumption of system
		}
	}
}