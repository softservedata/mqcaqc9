package com.softserve.edu.oms.data;

public final class UserRepository {

	private UserRepository() {
	}

	public static IUser getAdmin() {
//		User user = new User();
//		user.setFirstname("ivanka");
//		user.setLastname("horoshko");
//		user.setLogin("iva");
//		user.setPassword("qwerty");
//		user.setEmail("mail@gmail.com");
//		return new User()
//			.setFirstname("ivanka")
//			.setLastname("horoshko")
//			.setLogin("iva")
//			.setPassword("qwerty")
//			.setEmail("mail@gmail.com");
//		
		return User.get()
				.setFirstname("ivanka")
				.setLastname("horoshko")
				.setLogin("iva")
				.setPassword("qwerty")
				.setEmail("mail@gmail.com")
				.build();
	}
	
	public static IUser getCustomer() {
		//return new User("logincustomer", "logincustomer", "logincustomer",
		//		"qwerty", "mail@gmail.com");
		return User.get()
				.setFirstname("logincustomer")
				.setLastname("logincustomer")
				.setLogin("logincustomer")
				.setPassword("qwerty")
				.setEmail("mail@gmail.com")
				.build();
	}

	public static IUser getExist() {
		//return new User("abcd", "abcd", "abcd", "abcd", "mail@gmail.com");
		return User.get()
				.setFirstname("abcd")
				.setLastname("abcd")
				.setLogin("abcd")
				.setPassword("abcd")
				.setEmail("mail@gmail.com")
				.build();
	}

	public static IUser getNew() {
		//return new User("cccc", "cccc", "cccc", "qwerty", "cccc@gmail.com");
		return User.get()
				.setFirstname("cccc")
				.setLastname("cccc")
				.setLogin("cccc")
				.setPassword("qwerty")
				.setEmail("cccc@gmail.com")
				.build();
	}

	public static IUser getInvalid() {
		//return new User("dddd", "dddd", "dddd", "dddd", "dddd@gmail.com");
		return User.get()
				.setFirstname("ddd")
				.setLastname("ddd")
				.setLogin("ddd")
				.setPassword("dd")
				.setEmail("dddd@gmail.com")
				.build();
	}

}
