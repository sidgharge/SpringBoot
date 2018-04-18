package com.bridgelabz.copier;

public class App {
	public static void main(String[] args) {

//		UserDto dto = new UserDto();
//		dto.setFirstName("Sid");
//		dto.setLastName("Gharge");
//		dto.setActive(true);
//		
//		Converter converter = new Converter();
//		User userFromDto = converter.convert(dto, User.class);
//		System.out.println(userFromDto);
		
		Converter converter = new Converter();
		User user = new User();
		user.setActive(true);
		user.setFirstName("Rahul");
		user.setLastName("Mandalkar");
		user.setId(1);
		user.setPassword("Voila");
		
		UserDto dtoFromUser = converter.convert(user, UserDto.class);
		
		System.out.println(dtoFromUser);
		
	}
}
