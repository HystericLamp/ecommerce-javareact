package com.ecommerce.bcruz.repository.tests;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.ecommerce.bcruz.models.User;
import com.ecommerce.bcruz.repositories.UserRepository;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest
{
	@Autowired
	private UserRepository userRepository;
	
	@Test
	void shouldNotAllowDuplicateEmails()
	{
		User user1 = new User("Alice", "test@test.com", "pass");
        User user2 = new User("Bob", "test@test.com", "pass");

        userRepository.save(user1);

        assertThrows(Exception.class, () -> {
            userRepository.saveAndFlush(user2);
        });
	}
	
	@Test
	void shouldFailWhenManuallyInsertingDuplicateViaJdbc() throws Exception 
	{
	    User u1 = new User("A", "test@test.com", "pass");
	    User u2 = new User("B", "test@test.com", "pass");

	    userRepository.saveAndFlush(u1);

	    assertThrows(Exception.class, () -> {
	        userRepository.saveAndFlush(u2);
	    });
	}
}
