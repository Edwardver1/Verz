package com.verz;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.verz.domain.User;
import com.verz.domain.security.Role;
import com.verz.domain.security.UserRole;
import com.verz.service.UserService;
import com.verz.utility.SecurityUtility;



@SpringBootApplication
public class VerzApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(VerzApplication.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception {	
	    String str = "1996-03-16";
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate dateTime = LocalDate.parse(str, formatter);

			
		User user1 = new User();
		user1.setFirstName("Edward");
		user1.setLastName("Verameyenka");
		user1.setUsername("ed");
		user1.setBirthDate(dateTime);
		user1.setPassword(SecurityUtility.passwordEncoder().encode("1111"));
		user1.setEmail("verz.user@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1= new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_USER");
		
		
		userRoles.add(new UserRole(user1, role1));
		
		userService.createUser(user1, userRoles);
		
	}
}
