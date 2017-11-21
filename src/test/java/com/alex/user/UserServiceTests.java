package com.alex.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void whenPhoneNumberIsNotInTheDatabase() throws Exception{
        ArrayList<User> emptyArray = new ArrayList<>(0);

        User unsavedUser = new User();

        unsavedUser.setFirstName("Alex");
        unsavedUser.setLastName("Hall");
        unsavedUser.setPhoneNumber("9119119911");

        User savedUser = new User();

        savedUser.setId(123456);
        savedUser.setFirstName("Alex");
        savedUser.setLastName("Hall");
        savedUser.setPhoneNumber("9119119911");

        when(userRepository.findByPhoneNumber(any(String.class))).thenReturn(emptyArray);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User resultingUser = userService.saveOrFindUser(unsavedUser);

        assertEquals(savedUser.getId(), resultingUser.getId());
        assertEquals(savedUser.getFirstName(), resultingUser.getFirstName());
        assertEquals(savedUser.getLastName(), resultingUser.getLastName());
    }

    @Test
    public void whenPhoneNumberIsInTheDatabase() throws Exception{
        User unsavedUser = new User();

        unsavedUser.setFirstName("Alex");
        unsavedUser.setLastName("Hall");
        unsavedUser.setPhoneNumber("9119119911");

        User userFromDatabase = new User();

        userFromDatabase.setId(654321);
        userFromDatabase.setFirstName("Other");
        userFromDatabase.setLastName("User");
        userFromDatabase.setPhoneNumber("9119119911");

        when(userRepository.findByPhoneNumber(any(String.class))).thenReturn(Arrays.asList(userFromDatabase));

        User resultingUser = userService.saveOrFindUser(unsavedUser);

        assertEquals(userFromDatabase.getId(), resultingUser.getId());
        assertEquals(userFromDatabase.getFirstName(), resultingUser.getFirstName());
        assertEquals(userFromDatabase.getLastName(), resultingUser.getLastName());


    }
}
