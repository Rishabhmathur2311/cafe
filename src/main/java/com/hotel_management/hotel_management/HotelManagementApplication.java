package com.hotel_management.hotel_management;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hotel_management.hotel_management.Entity.User;
import com.hotel_management.hotel_management.Enum.UserRole;
import com.hotel_management.hotel_management.services.auth.AuthService;

@SpringBootApplication
public class HotelManagementApplication  implements CommandLineRunner {

	@Autowired
	private AuthService authService;



	public static void main(String[] args){
		SpringApplication.run(HotelManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		System.out.println("Starting Code.");

		User user = new User();

		user.setUserRole(UserRole.ADMIN);
		user.setPassword("123");
		user.setName("Rishabh");
		user.setEmail("rishab@gmail.com");

		User finalUser = this.authService.createAdmin(user);

		System.out.println(finalUser);

	}
}
